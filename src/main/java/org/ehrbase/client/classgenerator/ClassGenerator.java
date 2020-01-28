/*
 *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  This file is part of Project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.client.classgenerator;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMTypeInfo;
import com.squareup.javapoet.*;
import org.apache.commons.cli.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.text.CaseUtils;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.client.annotations.*;
import org.ehrbase.client.classgenerator.config.RmClassGeneratorConfig;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.introspect.TemplateIntrospect;
import org.ehrbase.client.introspect.node.*;
import org.ehrbase.client.terminology.ValueSet;
import org.ehrbase.ehr.encode.wrappers.SnakeCase;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ClassGenerator {

    private static final Options OPTIONS = new Options();
    private static final ArchieRMInfoLookup RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Map<Class, RmClassGeneratorConfig> configMap;


    private Map<String, Integer> currentFieldNameMap = new HashMap<>();
    private Map<String, Integer> currentClassNameMap = new HashMap<>();

    private ClassGeneratorResult currentResult;
    private String currentPackageName;
    private String currentMainClass;


    public ClassGenerator() {
        configMap = buildConfigMap();
    }

    private Map<Class, RmClassGeneratorConfig> buildConfigMap() {
        Reflections reflections = new Reflections(RmClassGeneratorConfig.class.getPackage().getName());
        Set<Class<? extends RmClassGeneratorConfig>> configs = reflections.getSubTypesOf(RmClassGeneratorConfig.class);

        return configs.stream().map(c -> {
            try {
                return c.getConstructor().newInstance();
            } catch (Exception e) {
                throw new ClientException(e.getMessage(), e);
            }
        }).collect(Collectors.toMap(RmClassGeneratorConfig::getRMClass, c -> c));
    }

    public ClassGeneratorResult generate(String packageName, OPERATIONALTEMPLATE operationalTemplate) {
        currentResult = new ClassGeneratorResult();
        currentPackageName = packageName;
        currentMainClass = "";
        ArchetypeNode root = new TemplateIntrospect(operationalTemplate).getRoot();
        String templateId = operationalTemplate.getTemplateId().getValue();
        TemplateNode templateNode = new TemplateNode(templateId, root.getArchetypeId(), root.getChildren(), templateId);
        TypeSpec build = build(templateNode);
        currentResult.addClass(currentPackageName + "." + currentMainClass.toLowerCase(), build);
        return currentResult;
    }

    private TypeSpec build(EntityNode archetypeNode) {
        Map<String, Integer> oldFieldNameMap = currentFieldNameMap;
        currentFieldNameMap = new HashMap<>();
        String className = buildClassName(archetypeNode.getName());
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(className);
        if (StringUtils.isBlank(currentMainClass)) {
            currentMainClass = className;
        }
        classBuilder.addModifiers(Modifier.PUBLIC);
        classBuilder.addAnnotation(AnnotationSpec.builder(Entity.class).build());

        if (archetypeNode instanceof ArchetypeNode) {
            AnnotationSpec archetypeAnnotation = AnnotationSpec.builder(Archetype.class).addMember(Archetype.VALUE, "$S", ((ArchetypeNode) archetypeNode).getArchetypeId()).build();
            classBuilder.addAnnotation(archetypeAnnotation);
        }

        if (archetypeNode instanceof TemplateNode) {
            AnnotationSpec templateAnnotation = AnnotationSpec.builder(Template.class).addMember(Template.VALUE, "$S", ((TemplateNode) archetypeNode).getTemplateId()).build();
            classBuilder.addAnnotation(templateAnnotation);
        }

        for (Map.Entry<String, Node> entry : archetypeNode.getChildren().entrySet()) {
            if (entry.getValue() instanceof EndNode) {
                addSimpleField(classBuilder, entry.getKey(), (EndNode) entry.getValue());
            } else if (entry.getValue() instanceof EntityNode) {
                addComplexField(classBuilder, entry.getKey(), (EntityNode) entry.getValue());
            } else if (entry.getValue() instanceof ChoiceNode) {
                addChoiceField(classBuilder, entry.getKey(), (ChoiceNode) entry.getValue());
            }
        }
        currentFieldNameMap = oldFieldNameMap;
        return classBuilder.build();
    }

    private void addChoiceField(TypeSpec.Builder classBuilder, String path, ChoiceNode choiceNode) {
        TypeSpec interfaceSpec = TypeSpec.interfaceBuilder(buildClassName(choiceNode.getName() + "_choice"))
                .addModifiers(Modifier.PUBLIC)
                .build();

        String interfacePackage = currentPackageName + "." + currentMainClass.toLowerCase() + ".definition";
        currentResult.addClass(interfacePackage, interfaceSpec);
        TypeName interfaceClassName = ClassName.get(interfacePackage, interfaceSpec.name);

        for (EndNode endNode : choiceNode.getNodes()) {
            Map<String, Integer> oldFieldNameMap = currentFieldNameMap;
            currentFieldNameMap = new HashMap<>();

            RMTypeInfo typeInfo = RM_INFO_LOOKUP.getTypeInfo(endNode.getClazz());
            TypeSpec.Builder builder = TypeSpec.classBuilder(buildClassName(choiceNode.getName() + "_" + endNode.getClazz().getSimpleName()))
                    .addSuperinterface(interfaceClassName)
                    .addModifiers(Modifier.PUBLIC)
                    .addModifiers(Modifier.STATIC)
                    .addAnnotation(AnnotationSpec.builder(Entity.class).build())
                    .addAnnotation(AnnotationSpec.builder(OptionFor.class).addMember(OptionFor.VALUE, "$S", typeInfo.getRmName()).build());
            addSimpleField(builder, "", endNode);
            TypeSpec typeSpec = builder.build();
            currentResult.addClass(currentPackageName + "." + currentMainClass.toLowerCase() + ".definition", typeSpec);
            currentFieldNameMap = oldFieldNameMap;
        }


        addField(classBuilder, path, choiceNode.getName(), interfaceClassName, new ValueSet(ValueSet.LOCAL, Collections.emptySet()), true);
    }

    private void addSimpleField(TypeSpec.Builder classBuilder, String path, EndNode endNode) {

        if (endNode.getClazz() == null) {
            logger.warn("No class for path {} ", path);
            return;
        }
        RmClassGeneratorConfig classGeneratorConfig = configMap.get(endNode.getClazz());
        if (classGeneratorConfig == null && !endNode.getClazz().getName().contains("java.lang")) {
            logger.debug("No ClassGenerator for {}", endNode.getClazz());
        }
        if (classGeneratorConfig == null || !classGeneratorConfig.isExpandField() || endNode instanceof SlotNode) {

            TypeName className = ClassName.get(Optional.ofNullable(endNode.getClazz()).orElse(Object.class));
            if (endNode instanceof SlotNode && ((SlotNode) endNode).isMulti()) {
                className = ParameterizedTypeName.get(ClassName.get(List.class), className);
            }

            addField(classBuilder, path, endNode.getName(), className, endNode.getValuset(), false);
        } else {
            Map<String, Field> fieldMap = Arrays.stream(FieldUtils.getAllFields(endNode.getClazz())).collect(Collectors.toMap(Field::getName, f -> f));
            classGeneratorConfig.getExpandFields().forEach(fieldName -> addField(classBuilder, path + "|" + new SnakeCase(fieldName).camelToSnake(), endNode.getName() + "_" + fieldName, ClassName.get(fieldMap.get(fieldName).getType()), endNode.getValuset(), false));
        }
    }

    private void addComplexField(TypeSpec.Builder classBuilder, String path, EntityNode node) {
        TypeSpec subSpec = build(node);

        String subSpecPackage = currentPackageName + "." + currentMainClass.toLowerCase() + ".definition";

        currentResult.addClass(subSpecPackage, subSpec);
        TypeName className = ClassName.get(subSpecPackage, subSpec.name);
        if (node.isMulti()) {
            className = ParameterizedTypeName.get(ClassName.get(List.class), className);
        }
        addField(classBuilder, path, node.getName(), className, new ValueSet(ValueSet.LOCAL, Collections.emptySet()), false);
    }

    private TypeSpec buildEnumValueSet(String name, ValueSet valuset) {
        TypeSpec.Builder enumBuilder = TypeSpec
                .enumBuilder(normalise(extractSubName(name), true))
                .addSuperinterface(EnumValueSet.class)
                .addModifiers(Modifier.PUBLIC);
        FieldSpec fieldSpec1 = FieldSpec.builder(ClassName.get(String.class), "value").addModifiers(Modifier.PRIVATE).build();
        enumBuilder.addField(fieldSpec1);
        FieldSpec fieldSpec2 = FieldSpec.builder(ClassName.get(String.class), "description").addModifiers(Modifier.PRIVATE).build();
        enumBuilder.addField(fieldSpec2);
        FieldSpec fieldSpec3 = FieldSpec.builder(ClassName.get(String.class), "terminologyId").addModifiers(Modifier.PRIVATE).build();
        enumBuilder.addField(fieldSpec3);
        FieldSpec fieldSpec4 = FieldSpec.builder(ClassName.get(String.class), "code").addModifiers(Modifier.PRIVATE).build();
        enumBuilder.addField(fieldSpec4);

        MethodSpec constructor = buildConstructor(fieldSpec1, fieldSpec2, fieldSpec3, fieldSpec4);
        enumBuilder.addMethod(constructor);
        valuset.getTherms().forEach(t -> {
            String fieldName = extractSubName(t.getValue());
            enumBuilder.addEnumConstant(normalise(fieldName, false).toUpperCase(), TypeSpec.anonymousClassBuilder("$S, $S, $S, $S", t.getValue(), t.getDescription(), StringUtils.substringBefore(valuset.getId(), ":"), t.getCode()).build());
        });

        enumBuilder.addMethod(buildGetter(fieldSpec1));
        enumBuilder.addMethod(buildGetter(fieldSpec2));
        enumBuilder.addMethod(buildGetter(fieldSpec3));
        enumBuilder.addMethod(buildGetter(fieldSpec4));
        return enumBuilder.build();
    }

    private MethodSpec buildConstructor(FieldSpec... fieldSpecs) {
        MethodSpec.Builder builder = MethodSpec.constructorBuilder();
        for (FieldSpec fieldSpec : fieldSpecs) {
            builder.addParameter(fieldSpec.type, fieldSpec.name)
                    .addStatement("this.$N = $N", fieldSpec.name, fieldSpec.name);
        }
        return builder.build();
    }

    private void addField(TypeSpec.Builder classBuilder, String path, String name, TypeName className, ValueSet valueSet, boolean addChoiceAnnotation) {


        if (CodePhrase.class.getName().equals(className.toString()) && CollectionUtils.isNotEmpty(valueSet.getTherms())) {

            TypeSpec enumValueSet = buildEnumValueSet(name, valueSet);
            String enumPackage;
            if (valueSet.getId().equals("local")) {
                enumPackage = currentPackageName + "." + currentMainClass.toLowerCase() + ".definition";
            } else {
                enumPackage = currentPackageName + ".shareddefinition";
            }
            currentResult.addClass(enumPackage, enumValueSet);
            className = ClassName.get(enumPackage, enumValueSet.name);
        }

        String fieldName = buildFieldName(name);
        FieldSpec.Builder builder = FieldSpec.builder(className, fieldName)
                .addAnnotation(AnnotationSpec.builder(Path.class).addMember(Path.VALUE, "$S", path).build())
                .addModifiers(Modifier.PRIVATE);

        if (addChoiceAnnotation) {
            builder.addAnnotation(Choice.class);
        }

        FieldSpec fieldSpec = builder
                .build();
        classBuilder.addField(fieldSpec);

        classBuilder.addMethod(buildSetter(fieldSpec));
        classBuilder.addMethod(buildGetter(fieldSpec));
    }

    private String buildFieldName(String name) {
        String[] strings = Arrays.stream(name.split(TemplateIntrospect.TERM_DIVIDER)).filter(StringUtils::isNotBlank).toArray(String[]::new);

        String fieldName = "";
        for (int i = 0; i < strings.length; i++) {
            fieldName = normalise(fieldName + "_" + strings[strings.length - (i + 1)], false);
            if (!currentFieldNameMap.containsKey(fieldName) && SourceVersion.isName(fieldName)) {
                break;
            }
        }

        if (currentFieldNameMap.containsKey(fieldName)) {
            currentFieldNameMap.put(fieldName, currentFieldNameMap.get(fieldName) + 1);
            fieldName = fieldName + currentFieldNameMap.get(fieldName);
        } else {
            currentFieldNameMap.put(fieldName, 1);
        }
        return fieldName;
    }

    private String buildClassName(String name) {

        String fieldName = normalise(name, true);

        if (currentClassNameMap.containsKey(fieldName)) {
            currentClassNameMap.put(fieldName, currentClassNameMap.get(fieldName) + 1);
            fieldName = fieldName + currentClassNameMap.get(fieldName);
        } else {
            currentClassNameMap.put(fieldName, 1);
        }
        return fieldName;
    }

    private MethodSpec buildSetter(FieldSpec fieldSpec) {

        return MethodSpec.methodBuilder("set" + StringUtils.capitalize(fieldSpec.name)).addModifiers(Modifier.PUBLIC)
                .addStatement(" this.$N = $N", fieldSpec.name, fieldSpec.name)
                .addParameter(fieldSpec.type, fieldSpec.name).build();
    }

    private MethodSpec buildGetter(FieldSpec fieldSpec) {
        String prefix;
        if (Boolean.class.getTypeName().equals(fieldSpec.type.toString())) {
            prefix = "is";
        } else {
            prefix = "get";
        }
        return MethodSpec.methodBuilder(prefix + StringUtils.capitalize(fieldSpec.name)).addModifiers(Modifier.PUBLIC)
                .addStatement(" return this.$N ", fieldSpec.name)
                .returns(fieldSpec.type).build();
    }


    private String extractSubName(String name) {
        String[] strings = name.split(TemplateIntrospect.TERM_DIVIDER);
        return strings[strings.length - 1];
    }

    private static String normalise(String name, boolean capitalizeFirstLetter) {
        if (StringUtils.isBlank(name) || name.equals("_")) {
            return RandomStringUtils.randomAlphabetic(10);
        }
        String normalisedString = name.replaceAll("[^A-Za-z0-9]", "_");
        return CaseUtils.toCamelCase(normalisedString, capitalizeFirstLetter, '_');
    }

    public static void main(String[] args) throws IOException, XmlException {


        OPTIONS.addOption("opt", true, "path to opt file");
        OPTIONS.addOption("package", true, "package name");
        OPTIONS.addOption("out", true, "path to output directory");
        OPTIONS.addOption("h", false, "show help");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(OPTIONS, args);
        } catch (ParseException e) {
            System.out.println(String.format("Error parsing commandline %s", e.getMessage()));
            showHelp();
        }

        if (cmd.hasOption("h")) {
            showHelp();
        }

        if (!cmd.hasOption("opt") || !cmd.hasOption("package") || !cmd.hasOption("out")) {
            showHelp();
        }
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(Paths.get(cmd.getOptionValue("opt")).toFile()).getTemplate();
        ClassGenerator cut = new ClassGenerator();
        ClassGeneratorResult generate = cut.generate(cmd.getOptionValue("package"), template);

        java.nio.file.Path fsRoot = Paths.get(cmd.getOptionValue("out"));

        generate.createFiles(fsRoot);
    }

    private static void showHelp() {

        HelpFormatter formatter = new HelpFormatter();

        formatter.printHelp("java -jar client-library.jar ", OPTIONS);

        System.exit(0);

    }
}
