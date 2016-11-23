/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.dmn.feel.lang;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.kie.dmn.feel.lang.impl.JavaBackedType;
import org.kie.dmn.feel.lang.types.BuiltInType;
import org.kie.dmn.feel.runtime.FEELFunction;
import org.kie.dmn.feel.runtime.Range;
import org.kie.dmn.feel.runtime.UnaryTest;

public interface Type {

    String getName();

    Object fromString( String value );

    String toString( Object value );

    public static Type determineTypeFromClass( Class<?> clazz ) {
        if( clazz == null ) {
            return BuiltInType.UNKNOWN;
        } else if( Number.class.isAssignableFrom(clazz) ) {
            return BuiltInType.NUMBER;
        } else if( String.class.isAssignableFrom(clazz) ) {
            return BuiltInType.STRING;
        } else if( LocalDate.class.isAssignableFrom(clazz) ) {
            return BuiltInType.DATE;
        } else if( LocalTime.class.isAssignableFrom(clazz) || OffsetTime.class.isAssignableFrom(clazz) ) {
            return BuiltInType.TIME;
        } else if( ZonedDateTime.class.isAssignableFrom(clazz) || OffsetDateTime.class.isAssignableFrom(clazz) || LocalDateTime.class.isAssignableFrom(clazz) ) {
            return BuiltInType.DATE_TIME;
        } else if( Duration.class.isAssignableFrom(clazz) || Period.class.isAssignableFrom(clazz) ) {
            return BuiltInType.DURATION;
        } else if( Boolean.class.isAssignableFrom(clazz) ) {
            return BuiltInType.BOOLEAN;
        } else if( UnaryTest.class.isAssignableFrom(clazz) ) {
            return BuiltInType.UNARY_TEST;
        } else if( Range.class.isAssignableFrom(clazz) ) {
            return BuiltInType.RANGE;
        } else if( FEELFunction.class.isAssignableFrom(clazz) ) {
            return BuiltInType.FUNCTION;
        } else if( List.class.isAssignableFrom(clazz) ) {
            return BuiltInType.LIST;
        } else if( Map.class.isAssignableFrom(clazz) ) {     // TODO not so sure about this one..
            return BuiltInType.CONTEXT;
        } else if (Stream.of(clazz.getMethods()).anyMatch(m->m.getAnnotation(FEELAccessor.class)!=null)) {
            return new JavaBackedType(clazz);
        }
        return BuiltInType.UNKNOWN;
    }
}
