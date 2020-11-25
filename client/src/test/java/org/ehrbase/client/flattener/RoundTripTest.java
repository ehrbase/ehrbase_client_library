/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
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

package org.ehrbase.client.flattener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.composition.Composition;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.SoftAssertions;
import org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.TestAllTypesEnV1Composition;
import org.ehrbase.client.templateprovider.TestDataTemplateProvider;
import org.ehrbase.serialisation.flatencoding.FlatFormat;
import org.ehrbase.serialisation.flatencoding.FlatJasonProvider;
import org.ehrbase.serialisation.flatencoding.FlatJson;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.ehrbase.test_data.composition.CompositionTestDataSimSDTJson;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;


public class RoundTripTest {

  class TestCase {
    long id;
    CompositionTestDataSimSDTJson simSDTJson;
    String templateId;
    Class<?> dtoClass;
    String[] missing;
    String[] extra;

    public TestCase(
        long id,
        CompositionTestDataSimSDTJson simSDTJson,
        String templateId,
        Class<?> dtoClass,
        String[] missing,
        String[] extra) {
      this.id = id;
      this.simSDTJson = simSDTJson;
      this.templateId = templateId;
      this.dtoClass = dtoClass;
      this.missing = missing;
      this.extra = extra;
    }
  }

  @Test
  public void testRoundTrip() throws IOException {

    List<TestCase> testCaseList = new ArrayList<>();

    testCaseList.add(
        new TestCase(
            1,
            CompositionTestDataSimSDTJson.ALL_TYPES,
            "test_all_types.en.v1",
            TestAllTypesEnV1Composition.class,
            new String[] {
              "Missing path: test_all_types/test_all_types:0/identifier|id, value: 55175056",
              "Missing path: test_all_types/test_all_types:0/proportion_any|type, value: 1"
            },
            new String[] {
                    "Extra path: test_all_types/test_all_types:0/identifier, value: 55175056",
                    "Extra path: test_all_types/test_all_types:0/proportion_any|type, value: 1.0",
                    "Extra path: test_all_types/test_all_types:0/coded_text|value, value: value1",
                    "Extra path: test_all_types/test_all_types:0/coded_text_terminology|value, value: .HCXbqCyQtseLkDyKS,QLpOdDZxrEJ",
                    "Extra path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types:0/current_activity/timing, value: P1D",
                    "Extra path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types:0/current_activity/timing|formalism, value: ISO8601",
                    "Extra path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types2:0/ism_transition/current_state|code, value: 526",
                    "Extra path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types2:0/ism_transition/current_state|value, value: planned",
                    "Extra path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types2:0/ism_transition/current_state|terminology, value: openehr",
            }));

    SoftAssertions softly = new SoftAssertions();

    for (TestCase testCase : testCaseList) {
      checkTestCase(testCase, softly);
    }

    softly.assertAll();
  }

  public void checkTestCase(TestCase testCase, SoftAssertions softly) throws IOException {

    String value = IOUtils.toString(testCase.simSDTJson.getStream(), UTF_8);
    FlatJson flatJson =
        new FlatJasonProvider(new TestDataTemplateProvider())
            .buildFlatJson(FlatFormat.SIM_SDT, testCase.templateId);

    Composition composition = flatJson.unmarshal(value);

    Flattener flattener = new Flattener(new TestDataTemplateProvider());
    Object flatten = flattener.flatten(composition, testCase.dtoClass);

    Unflattener unflattener = new Unflattener(new TestDataTemplateProvider());
    RMObject actual = unflattener.unflatten(flatten);

    String actualFlat = flatJson.marshal(actual);

    List<String> errors = compere(actualFlat, value);

    softly.assertThat(errors)
            .filteredOn(s -> s.startsWith("Missing"))
            .as("Test Case %s", testCase.id)
            .containsExactlyInAnyOrder(
                    testCase.missing
            );

    String[] extra = {"Extra path: test_all_types/test_all_types:0/identifier, value: 55175056",
            "Extra path: test_all_types/test_all_types:0/proportion_any|type, value: 1.0"};
    softly.assertThat(errors)
            .filteredOn(s -> s.startsWith("Extra"))
            .as("Test Case %s", testCase.id)
            .containsExactlyInAnyOrder(
                    testCase.extra
            );


  }

  private static List<String> compere(String actualJson, String expectedJson) throws JsonProcessingException {
    List<String> errors = new ArrayList<>();
    ObjectMapper objectMapper = JacksonUtil.getObjectMapper();

    Map<String, Object> actual = objectMapper.readValue(actualJson, Map.class);
    Map<String, Object> expected = objectMapper.readValue(expectedJson, Map.class);

    actual.forEach((key, value) -> {
      if (!expected.containsKey(key) || !expected.get(key).equals(value)) {
        errors.add(String.format("Missing path: %s, value: %s", key, value));
      }
    });

    expected.forEach((key, value) -> {
      if (!actual.containsKey(key) || !actual.get(key).equals(value)) {
        errors.add(String.format("Extra path: %s, value: %s", key, value));
      }
    });

    return errors;
  }
}