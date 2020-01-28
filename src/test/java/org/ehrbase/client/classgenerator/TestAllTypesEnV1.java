package org.ehrbase.client.classgenerator;

import com.nedap.archie.rm.datastructures.ItemStructure;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.ehrbase.client.annotations.*;

import java.net.URI;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.test_all_types.v1")
@Template("test_all_types.en.v1")
public class TestAllTypesEnV1 {
    @Path("/content[openEHR-EHR-EVALUATION.test_all_types.v1]")
    private List<TestAllTypes> testAllTypes;

    @Path("/context/end_time|value")
    private TemporalAccessor endTimeValue;

    @Path("/content[openEHR-EHR-SECTION.test_all_types.v1]")
    private List<TestAllTypes2> testAllTypes2;

    @Path("/language")
    private CodePhrase language;

    @Path("/context/health_care_facility")
    private PartyIdentified healthCareFacility;

    @Path("/composer|external_ref")
    private PartyRef composerExternalref;

    @Path("/context/setting|defining_code")
    private CodePhrase settingDefiningcode;

    @Path("/territory")
    private CodePhrase territory;

    @Path("/context/other_context[at0004]/item[at0005]/value|defining_code")
    private ContextCodedTextDefiningcode contextCodedTextDefiningcode;

    @Path("/content[openEHR-EHR-OBSERVATION.test_all_types.v1]")
    private List<TestAllTypes7> testAllTypes3;

    @Path("/context/location")
    private String location;

    @Path("/context/start_time|value")
    private TemporalAccessor startTimeValue;

    public void setTestAllTypes(List<TestAllTypes> testAllTypes) {
        this.testAllTypes = testAllTypes;
    }

    public List<TestAllTypes> getTestAllTypes() {
        return this.testAllTypes;
    }

    public void setEndTimeValue(TemporalAccessor endTimeValue) {
        this.endTimeValue = endTimeValue;
    }

    public TemporalAccessor getEndTimeValue() {
        return this.endTimeValue;
    }

    public void setTestAllTypes2(List<TestAllTypes2> testAllTypes2) {
        this.testAllTypes2 = testAllTypes2;
    }

    public List<TestAllTypes2> getTestAllTypes2() {
        return this.testAllTypes2;
    }

    public void setLanguage(CodePhrase language) {
        this.language = language;
    }

    public CodePhrase getLanguage() {
        return this.language;
    }

    public void setHealthCareFacility(PartyIdentified healthCareFacility) {
        this.healthCareFacility = healthCareFacility;
    }

    public PartyIdentified getHealthCareFacility() {
        return this.healthCareFacility;
    }

    public void setComposerExternalref(PartyRef composerExternalref) {
        this.composerExternalref = composerExternalref;
    }

    public PartyRef getComposerExternalref() {
        return this.composerExternalref;
    }

    public void setSettingDefiningcode(CodePhrase settingDefiningcode) {
        this.settingDefiningcode = settingDefiningcode;
    }

    public CodePhrase getSettingDefiningcode() {
        return this.settingDefiningcode;
    }

    public void setTerritory(CodePhrase territory) {
        this.territory = territory;
    }

    public CodePhrase getTerritory() {
        return this.territory;
    }

    public void setContextCodedTextDefiningcode(
            ContextCodedTextDefiningcode contextCodedTextDefiningcode) {
        this.contextCodedTextDefiningcode = contextCodedTextDefiningcode;
    }

    public ContextCodedTextDefiningcode getContextCodedTextDefiningcode() {
        return this.contextCodedTextDefiningcode;
    }

    public void setTestAllTypes3(List<TestAllTypes7> testAllTypes3) {
        this.testAllTypes3 = testAllTypes3;
    }

    public List<TestAllTypes7> getTestAllTypes3() {
        return this.testAllTypes3;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    public void setStartTimeValue(TemporalAccessor startTimeValue) {
        this.startTimeValue = startTimeValue;
    }

    public TemporalAccessor getStartTimeValue() {
        return this.startTimeValue;
    }

    @Entity
    @Archetype("openEHR-EHR-EVALUATION.test_all_types.v1")
    public static class TestAllTypes {
        @Path("/data[at0001]/items[at0004]/value")
        private DvInterval intervalQuantity;

        @Path("/data[at0001]/items[at0006]/items[at0007]/items[at0008]/items[at0010]/value|value")
        private String text2Value;

        @Path("/data[at0001]/items[at0003]/value")
        private DvInterval intervalCount;

        @Path("/data[at0001]/items[at0005]/value")
        private DvInterval intervalDatetime;

        @Path("/data[at0001]/items[at0002]/value|value")
        private URI uriValue;

        @Path("/language")
        private CodePhrase language;

        @Path("/data[at0001]/items[at0009]/value")
        @Choice
        private ArbolChoiceChoice choice;

        @Path("/subject|external_ref")
        private PartyRef subjectExternalref;

        public void setIntervalQuantity(DvInterval intervalQuantity) {
            this.intervalQuantity = intervalQuantity;
        }

        public DvInterval getIntervalQuantity() {
            return this.intervalQuantity;
        }

        public void setText2Value(String text2Value) {
            this.text2Value = text2Value;
        }

        public String getText2Value() {
            return this.text2Value;
        }

        public void setIntervalCount(DvInterval intervalCount) {
            this.intervalCount = intervalCount;
        }

        public DvInterval getIntervalCount() {
            return this.intervalCount;
        }

        public void setIntervalDatetime(DvInterval intervalDatetime) {
            this.intervalDatetime = intervalDatetime;
        }

        public DvInterval getIntervalDatetime() {
            return this.intervalDatetime;
        }

        public void setUriValue(URI uriValue) {
            this.uriValue = uriValue;
        }

        public URI getUriValue() {
            return this.uriValue;
        }

        public void setLanguage(CodePhrase language) {
            this.language = language;
        }

        public CodePhrase getLanguage() {
            return this.language;
        }

        public void setChoice(ArbolChoiceChoice choice) {
            this.choice = choice;
        }

        public ArbolChoiceChoice getChoice() {
            return this.choice;
        }

        public void setSubjectExternalref(PartyRef subjectExternalref) {
            this.subjectExternalref = subjectExternalref;
        }

        public PartyRef getSubjectExternalref() {
            return this.subjectExternalref;
        }

        public interface ArbolChoiceChoice {
        }

        @Entity
        @OptionFor("DV_QUANTITY")
        public static class ArbolChoiceDvquantity implements ArbolChoiceChoice {
            @Path("|magnitude")
            private Double choiceMagnitude;

            @Path("|units")
            private String choiceUnits;

            public void setChoiceMagnitude(Double choiceMagnitude) {
                this.choiceMagnitude = choiceMagnitude;
            }

            public Double getChoiceMagnitude() {
                return this.choiceMagnitude;
            }

            public void setChoiceUnits(String choiceUnits) {
                this.choiceUnits = choiceUnits;
            }

            public String getChoiceUnits() {
                return this.choiceUnits;
            }
        }

        @Entity
        @OptionFor("DV_COUNT")
        public static class ArbolChoiceDvcount implements ArbolChoiceChoice {
            @Path("|magnitude")
            private Long choiceMagnitude;

            public void setChoiceMagnitude(Long choiceMagnitude) {
                this.choiceMagnitude = choiceMagnitude;
            }

            public Long getChoiceMagnitude() {
                return this.choiceMagnitude;
            }
        }
    }

    @Entity
    @Archetype("openEHR-EHR-SECTION.test_all_types.v1")
    public static class TestAllTypes2 {
        @Path("/items[at0001]/items[at0002]/items[openEHR-EHR-ACTION.test_all_types.v1]")
        private List<TestAllTypes3> testAllTypes;

        @Path("/items[at0001]/items[at0002]/items[openEHR-EHR-INSTRUCTION.test_all_types.v1]")
        private List<TestAllTypes5> testAllTypes2;

        @Path("/items[at0001]/items[openEHR-EHR-ADMIN_ENTRY.test_all_types.v1]")
        private List<TestAllTypes6> testAllTypes3;

        public void setTestAllTypes(List<TestAllTypes3> testAllTypes) {
            this.testAllTypes = testAllTypes;
        }

        public List<TestAllTypes3> getTestAllTypes() {
            return this.testAllTypes;
        }

        public void setTestAllTypes2(List<TestAllTypes5> testAllTypes2) {
            this.testAllTypes2 = testAllTypes2;
        }

        public List<TestAllTypes5> getTestAllTypes2() {
            return this.testAllTypes2;
        }

        public void setTestAllTypes3(List<TestAllTypes6> testAllTypes3) {
            this.testAllTypes3 = testAllTypes3;
        }

        public List<TestAllTypes6> getTestAllTypes3() {
            return this.testAllTypes3;
        }

        @Entity
        @Archetype("openEHR-EHR-ACTION.test_all_types.v1")
        public static class TestAllTypes3 {
            @Path("/time|value")
            private TemporalAccessor timeValue;

            @Path("/ism_transition[at0005]/careflow_step|defining_code")
            private CompletedDefiningcode completedDefiningcode;

            @Path("/ism_transition[at0005]/current_state|defining_code")
            private CodePhrase completedDefiningcode2;

            @Path("/ism_transition[at0003]/current_state|defining_code")
            private CodePhrase plannedDefiningcode;

            @Path("/ism_transition[at0004]/current_state|defining_code")
            private CodePhrase activeDefiningcode;

            @Path("/ism_transition[at0003]/careflow_step|defining_code")
            private PlannedDefiningcode plannedDefiningcode2;

            @Path("/ism_transition[at0004]/careflow_step|defining_code")
            private ActiveDefiningcode activeDefiningcode2;

            @Path("/description[at0001]/items[openEHR-EHR-CLUSTER.test_all_types.v1]")
            private List<TestAllTypes4> testAllTypes;

            public void setTimeValue(TemporalAccessor timeValue) {
                this.timeValue = timeValue;
            }

            public TemporalAccessor getTimeValue() {
                return this.timeValue;
            }

            public void setCompletedDefiningcode(CompletedDefiningcode completedDefiningcode) {
                this.completedDefiningcode = completedDefiningcode;
            }

            public CompletedDefiningcode getCompletedDefiningcode() {
                return this.completedDefiningcode;
            }

            public void setCompletedDefiningcode2(CodePhrase completedDefiningcode2) {
                this.completedDefiningcode2 = completedDefiningcode2;
            }

            public CodePhrase getCompletedDefiningcode2() {
                return this.completedDefiningcode2;
            }

            public void setPlannedDefiningcode(CodePhrase plannedDefiningcode) {
                this.plannedDefiningcode = plannedDefiningcode;
            }

            public CodePhrase getPlannedDefiningcode() {
                return this.plannedDefiningcode;
            }

            public void setActiveDefiningcode(CodePhrase activeDefiningcode) {
                this.activeDefiningcode = activeDefiningcode;
            }

            public CodePhrase getActiveDefiningcode() {
                return this.activeDefiningcode;
            }

            public void setPlannedDefiningcode2(PlannedDefiningcode plannedDefiningcode2) {
                this.plannedDefiningcode2 = plannedDefiningcode2;
            }

            public PlannedDefiningcode getPlannedDefiningcode2() {
                return this.plannedDefiningcode2;
            }

            public void setActiveDefiningcode2(ActiveDefiningcode activeDefiningcode2) {
                this.activeDefiningcode2 = activeDefiningcode2;
            }

            public ActiveDefiningcode getActiveDefiningcode2() {
                return this.activeDefiningcode2;
            }

            public void setTestAllTypes(List<TestAllTypes4> testAllTypes) {
                this.testAllTypes = testAllTypes;
            }

            public List<TestAllTypes4> getTestAllTypes() {
                return this.testAllTypes;
            }

            public enum CompletedDefiningcode implements EnumValueSet {
                COMPLETED("Completed", "*", "local", "at0005");

                private String value;

                private String description;

                private String terminologyId;

                private String code;

                CompletedDefiningcode(String value, String description, String terminologyId, String code) {
                    this.value = value;
                    this.description = description;
                    this.terminologyId = terminologyId;
                    this.code = code;
                }

                public String getValue() {
                    return this.value;
                }

                public String getDescription() {
                    return this.description;
                }

                public String getTerminologyId() {
                    return this.terminologyId;
                }

                public String getCode() {
                    return this.code;
                }
            }

            public enum PlannedDefiningcode implements EnumValueSet {
                PLANNED("Planned", "*", "local", "at0003");

                private String value;

                private String description;

                private String terminologyId;

                private String code;

                PlannedDefiningcode(String value, String description, String terminologyId, String code) {
                    this.value = value;
                    this.description = description;
                    this.terminologyId = terminologyId;
                    this.code = code;
                }

                public String getValue() {
                    return this.value;
                }

                public String getDescription() {
                    return this.description;
                }

                public String getTerminologyId() {
                    return this.terminologyId;
                }

                public String getCode() {
                    return this.code;
                }
            }

            public enum ActiveDefiningcode implements EnumValueSet {
                ACTIVE("Active", "*", "local", "at0004");

                private String value;

                private String description;

                private String terminologyId;

                private String code;

                ActiveDefiningcode(String value, String description, String terminologyId, String code) {
                    this.value = value;
                    this.description = description;
                    this.terminologyId = terminologyId;
                    this.code = code;
                }

                public String getValue() {
                    return this.value;
                }

                public String getDescription() {
                    return this.description;
                }

                public String getTerminologyId() {
                    return this.terminologyId;
                }

                public String getCode() {
                    return this.code;
                }
            }

            @Entity
            @Archetype("openEHR-EHR-CLUSTER.test_all_types.v1")
            public static class TestAllTypes4 {
                @Path("/items[at0001]/items[at0002]/items[at0003]/value|value")
                private Boolean boolean2Value;

                public void setBoolean2Value(Boolean boolean2Value) {
                    this.boolean2Value = boolean2Value;
                }

                public Boolean isBoolean2Value() {
                    return this.boolean2Value;
                }
            }
        }

        @Entity
        @Archetype("openEHR-EHR-INSTRUCTION.test_all_types.v1")
        public static class TestAllTypes5 {
            @Path("/narrative|value")
            private String narrativeValue;

            @Path("/language")
            private CodePhrase language;

            @Path("/activities[at0001]/description")
            private ItemStructure description;

            @Path("/activities[at0001]/description[at0002]/items[at0003]/value|value")
            private Temporal partialDateValue;

            @Path("/subject|external_ref")
            private PartyRef subjectExternalref;

            @Path("/activities[at0001]/action_archetype_id")
            private String currentActivity;

            @Path("/activities[at0001]/description[at0002]/items[at0004]/value|value")
            private TemporalAccessor partialDatetimeValue;

            public void setNarrativeValue(String narrativeValue) {
                this.narrativeValue = narrativeValue;
            }

            public String getNarrativeValue() {
                return this.narrativeValue;
            }

            public void setLanguage(CodePhrase language) {
                this.language = language;
            }

            public CodePhrase getLanguage() {
                return this.language;
            }

            public void setDescription(ItemStructure description) {
                this.description = description;
            }

            public ItemStructure getDescription() {
                return this.description;
            }

            public void setPartialDateValue(Temporal partialDateValue) {
                this.partialDateValue = partialDateValue;
            }

            public Temporal getPartialDateValue() {
                return this.partialDateValue;
            }

            public void setSubjectExternalref(PartyRef subjectExternalref) {
                this.subjectExternalref = subjectExternalref;
            }

            public PartyRef getSubjectExternalref() {
                return this.subjectExternalref;
            }

            public void setCurrentActivity(String currentActivity) {
                this.currentActivity = currentActivity;
            }

            public String getCurrentActivity() {
                return this.currentActivity;
            }

            public void setPartialDatetimeValue(TemporalAccessor partialDatetimeValue) {
                this.partialDatetimeValue = partialDatetimeValue;
            }

            public TemporalAccessor getPartialDatetimeValue() {
                return this.partialDatetimeValue;
            }
        }

        @Entity
        @Archetype("openEHR-EHR-ADMIN_ENTRY.test_all_types.v1")
        public static class TestAllTypes6 {
            @Path("/language")
            private CodePhrase language;

            @Path("/data[at0001]/item[at0002]/value|magnitude")
            private Long count3Magnitude;

            public void setLanguage(CodePhrase language) {
                this.language = language;
            }

            public CodePhrase getLanguage() {
                return this.language;
            }

            public void setCount3Magnitude(Long count3Magnitude) {
                this.count3Magnitude = count3Magnitude;
            }

            public Long getCount3Magnitude() {
                return this.count3Magnitude;
            }
        }
    }

    public enum ContextCodedTextDefiningcode implements EnumValueSet {
        VALUE3("value3", "*", "local", "at0008"),

        VALUE1("value1", "*", "local", "at0006"),

        VALUE2("value2", "*", "local", "at0007");

        private String value;

        private String description;

        private String terminologyId;

        private String code;

        ContextCodedTextDefiningcode(String value, String description, String terminologyId,
                                     String code) {
            this.value = value;
            this.description = description;
            this.terminologyId = terminologyId;
            this.code = code;
        }

        public String getValue() {
            return this.value;
        }

        public String getDescription() {
            return this.description;
        }

        public String getTerminologyId() {
            return this.terminologyId;
        }

        public String getCode() {
            return this.code;
        }
    }

    @Entity
    @Archetype("openEHR-EHR-OBSERVATION.test_all_types.v1")
    public static class TestAllTypes7 {
        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0019]/value")
        private DvMultimedia multimediaAny;

        @Path("/language")
        private CodePhrase language;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0012]/value|value")
        private TemporalAccessor timeValue;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0020]/value")
        private DvParsable parsableAny;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0021]/value")
        private DvIdentifier identifier;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|value")
        private String textValue;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|defining_code")
        private CodePhrase codedTextDefiningcode;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0022]/value")
        private DvProportion proportionAny;

        @Path("/data[at0001]/events[at0002]/time|value")
        private TemporalAccessor timeValue2;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006]/value|defining_code")
        private CodePhrase codedTextTerminologyDefiningcode;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0007]/value|magnitude")
        private Double quantityMagnitude;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0007]/value|units")
        private String quantityUnits;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0008]/value|magnitude")
        private Long countMagnitude;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0009]/value|value")
        private Temporal dateValue;

        @Path("/subject|external_ref")
        private PartyRef subjectExternalref;

        @Path("/data[at0001]/origin|value")
        private TemporalAccessor originValue;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0018]/value|value")
        private TemporalAmount durationAnyValue;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0013]/value")
        private DvOrdinal ordinal;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0017]/value|value")
        private Boolean booleanValue;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0010]/value|value")
        private TemporalAccessor datetimeValue;

        @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0011]/value|value")
        private TemporalAccessor datetimeAnyValue;

        public void setMultimediaAny(DvMultimedia multimediaAny) {
            this.multimediaAny = multimediaAny;
        }

        public DvMultimedia getMultimediaAny() {
            return this.multimediaAny;
        }

        public void setLanguage(CodePhrase language) {
            this.language = language;
        }

        public CodePhrase getLanguage() {
            return this.language;
        }

        public void setTimeValue(TemporalAccessor timeValue) {
            this.timeValue = timeValue;
        }

        public TemporalAccessor getTimeValue() {
            return this.timeValue;
        }

        public void setParsableAny(DvParsable parsableAny) {
            this.parsableAny = parsableAny;
        }

        public DvParsable getParsableAny() {
            return this.parsableAny;
        }

        public void setIdentifier(DvIdentifier identifier) {
            this.identifier = identifier;
        }

        public DvIdentifier getIdentifier() {
            return this.identifier;
        }

        public void setTextValue(String textValue) {
            this.textValue = textValue;
        }

        public String getTextValue() {
            return this.textValue;
        }

        public void setCodedTextDefiningcode(CodePhrase codedTextDefiningcode) {
            this.codedTextDefiningcode = codedTextDefiningcode;
        }

        public CodePhrase getCodedTextDefiningcode() {
            return this.codedTextDefiningcode;
        }

        public void setProportionAny(DvProportion proportionAny) {
            this.proportionAny = proportionAny;
        }

        public DvProportion getProportionAny() {
            return this.proportionAny;
        }

        public void setTimeValue2(TemporalAccessor timeValue2) {
            this.timeValue2 = timeValue2;
        }

        public TemporalAccessor getTimeValue2() {
            return this.timeValue2;
        }

        public void setCodedTextTerminologyDefiningcode(CodePhrase codedTextTerminologyDefiningcode) {
            this.codedTextTerminologyDefiningcode = codedTextTerminologyDefiningcode;
        }

        public CodePhrase getCodedTextTerminologyDefiningcode() {
            return this.codedTextTerminologyDefiningcode;
        }

        public void setQuantityMagnitude(Double quantityMagnitude) {
            this.quantityMagnitude = quantityMagnitude;
        }

        public Double getQuantityMagnitude() {
            return this.quantityMagnitude;
        }

        public void setQuantityUnits(String quantityUnits) {
            this.quantityUnits = quantityUnits;
        }

        public String getQuantityUnits() {
            return this.quantityUnits;
        }

        public void setCountMagnitude(Long countMagnitude) {
            this.countMagnitude = countMagnitude;
        }

        public Long getCountMagnitude() {
            return this.countMagnitude;
        }

        public void setDateValue(Temporal dateValue) {
            this.dateValue = dateValue;
        }

        public Temporal getDateValue() {
            return this.dateValue;
        }

        public void setSubjectExternalref(PartyRef subjectExternalref) {
            this.subjectExternalref = subjectExternalref;
        }

        public PartyRef getSubjectExternalref() {
            return this.subjectExternalref;
        }

        public void setOriginValue(TemporalAccessor originValue) {
            this.originValue = originValue;
        }

        public TemporalAccessor getOriginValue() {
            return this.originValue;
        }

        public void setDurationAnyValue(TemporalAmount durationAnyValue) {
            this.durationAnyValue = durationAnyValue;
        }

        public TemporalAmount getDurationAnyValue() {
            return this.durationAnyValue;
        }

        public void setOrdinal(DvOrdinal ordinal) {
            this.ordinal = ordinal;
        }

        public DvOrdinal getOrdinal() {
            return this.ordinal;
        }

        public void setBooleanValue(Boolean booleanValue) {
            this.booleanValue = booleanValue;
        }

        public Boolean isBooleanValue() {
            return this.booleanValue;
        }

        public void setDatetimeValue(TemporalAccessor datetimeValue) {
            this.datetimeValue = datetimeValue;
        }

        public TemporalAccessor getDatetimeValue() {
            return this.datetimeValue;
        }

        public void setDatetimeAnyValue(TemporalAccessor datetimeAnyValue) {
            this.datetimeAnyValue = datetimeAnyValue;
        }

        public TemporalAccessor getDatetimeAnyValue() {
            return this.datetimeAnyValue;
        }
    }
}