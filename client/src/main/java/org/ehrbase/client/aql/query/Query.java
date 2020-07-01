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

package org.ehrbase.client.aql.query;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.containment.ContainmentExpression;
import org.ehrbase.client.aql.field.AqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.aql.record.Record;
import org.ehrbase.client.aql.record.Record1;
import org.ehrbase.client.aql.record.Record2;
import org.ehrbase.client.aql.record.Record3;

import java.util.Arrays;

public interface Query<T extends Record> {

    static Query<Record> buildNativeQuery(String aql, Class<?>... expected) {
        return new NativeQuery<>(aql, Arrays.stream(expected).map(AqlField::create).toArray(AqlField<?>[]::new));
    }

    static <T1> Query<Record1<T1>> buildNativeQuery(String aql, Class<T1> expected1) {
        return new NativeQuery<>(aql, AqlField.create(expected1));
    }

    static <T1, T2> Query<Record2<T1, T2>> buildNativeQuery(String aql, Class<T1> expected1, Class<T2> expected2) {
        return new NativeQuery<>(aql, AqlField.create(expected1), AqlField.create(expected2));
    }

    static <T1, T2, T3> Query<Record3<T1, T2, T3>> buildNativeQuery(String aql, Class<T1> expected1, Class<T2> expected2, Class<T3> expected3) {
        return new NativeQuery<>(aql, AqlField.create(expected1), AqlField.create(expected2), AqlField.create(expected3));
    }

    static Containment buildContainment(Class<?> entityClass) {
        Archetype annotation = entityClass.getAnnotation(Archetype.class);
        return new Containment(annotation.value());
    }

    static EntityQuery<Record> buildEntityQuery(ContainmentExpression containment, SelectAqlField<?>... selectFields) {
        return new EntityQuery<>(containment, selectFields);
    }

    static <T1> EntityQuery<Record1<T1>> buildEntityQuery(ContainmentExpression containment, SelectAqlField<T1> selectField1) {
        return new EntityQuery<>(containment, selectField1);
    }

    static <T1, T2> EntityQuery<Record2<T1, T2>> buildEntityQuery(ContainmentExpression containment, SelectAqlField<T1> selectField1, SelectAqlField<T2> selectField2) {
        return new EntityQuery<>(containment, selectField1, selectField2);
    }

    static <T1, T2, T3> EntityQuery<Record3<T1, T2, T3>> buildEntityQuery(ContainmentExpression containment, SelectAqlField<T1> selectField1, SelectAqlField<T2> selectField2, SelectAqlField<T3> selectField3) {
        return new EntityQuery<>(containment, selectField1, selectField2, selectField3);
    }

    String buildAql();

    AqlField<?>[] fields();
}
