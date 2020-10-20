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

package org.ehrbase.serialisation.std;


import com.nedap.archie.rm.composition.Composition;
import org.apache.commons.io.IOUtils;
import org.ehrbase.serialisation.templateprovider.TestDataTemplateProvider;
import org.ehrbase.test_data.composition.CompositionTestDataSimSDTJson;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ehrbase.serialisation.std.marshal.FlatJsonMarshallerTest.compere;

public class FlatJsonTest {

    @Test
    public void roundTrip() throws IOException {
        TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();
        FlatJson cut = new FlatJasonProvider(templateProvider).buildFlatJson(FlatFormat.SIM_SDT, "Corona_Anamnese");

        String flat = IOUtils.toString(CompositionTestDataSimSDTJson.CORONA.getStream(), StandardCharsets.UTF_8);
        Composition unmarshal = cut.unmarshal(flat);

        assertThat(unmarshal).isNotNull();

        String actual = cut.marshal(unmarshal);

        String expected = IOUtils.toString(CompositionTestDataSimSDTJson.CORONA.getStream(), StandardCharsets.UTF_8);

        List<String> errors = compere(actual, expected);

        assertThat(errors)
                .filteredOn(s -> s.startsWith("Missing"))
                .containsExactlyInAnyOrder();

        assertThat(errors)
                .filteredOn(s -> s.startsWith("Extra"))
                .containsExactlyInAnyOrder();
    }

}