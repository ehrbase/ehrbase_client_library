/*
 *
 *  *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.serialisation.walker.defaultvalues;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.client.classgenerator.EnumValueSet;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.ehrbase.util.exception.SdkException;

public class DefaultValues {

  private final Map<DefaultValuePath, Object> defaultValueMap;
  private static final ObjectMapper OBJECT_MAPPER = JacksonUtil.getObjectMapper();

  public DefaultValues() {
    defaultValueMap = new EnumMap<>(DefaultValuePath.class);
  }

  public DefaultValues(Map<String, String> flat) {
    defaultValueMap = new EnumMap<>(DefaultValuePath.class);

    Arrays.stream(DefaultValuePath.values())
        .forEach(
            path -> {
              Map<String, String> subValues =
                  flat.entrySet().stream()
                      .filter(e -> StringUtils.startsWith("ctx/" + path.getPath(), e.getKey()))
                      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
              if (!subValues.isEmpty()) {
                switch (path) {
                  case LANGUAGE:
                  case TERRITORY:
                    String value =
                        subValues.values().stream()
                            .map(DefaultValues::read)
                            .findAny()
                            .orElseThrow();
                    defaultValueMap.put(
                        path,
                        Arrays.stream(path.getType().getEnumConstants())
                            .map(EnumValueSet.class::cast)
                            .filter(e -> e.getCode().equals(value))
                            .findAny()
                            .orElseThrow());
                    break;
                }
              }
            });
  }

  private static String read(String s) {
    try {
      return OBJECT_MAPPER.readValue(s, String.class);
    } catch (JsonProcessingException jsonProcessingException) {
      throw new SdkException(jsonProcessingException.getMessage());
    }
  }

  public void addDefaultValue(DefaultValuePath path, Object value) {

    if (value == null) {

      defaultValueMap.remove(path);
    } else if (path.getType().isAssignableFrom(value.getClass())) {
      defaultValueMap.put(path, value);
    } else {
      throw new SdkException(
          String.format(
              "Can not set %s can not cast %s to %s",
              path, path.getType().getSimpleName(), value.getClass().getSimpleName()));
    }
  }

  public void removeDefaultValue(DefaultValuePath path) {
    defaultValueMap.remove(path);
  }

  public <T> T getDefaultValue(DefaultValuePath path, Class<T> clazz) {
    return (T) defaultValueMap.get(path);
  }
}