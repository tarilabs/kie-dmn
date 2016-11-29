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

package org.kie.dmn.feel.runtime.functions;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import org.kie.dmn.feel.runtime.events.FEELEvent;
import org.kie.dmn.feel.runtime.events.InvalidParametersEvent;
import org.kie.dmn.feel.runtime.events.FEELEvent.Severity;
import org.kie.dmn.feel.util.Either;

public class DateFunction
        extends BaseFEELFunction {

    public DateFunction() {
        super( "date" );
    }

    public Either<FEELEvent, TemporalAccessor> apply(@ParameterName( "from" ) String val) {
        if ( val == null ) {
            return Either.ofLeft(new InvalidParametersEvent(Severity.ERROR, "from", "cannot be null"));
        }
        
        try {
            return Either.ofRight( LocalDate.from( DateTimeFormatter.ISO_DATE.parse( val ) ) );
        } catch (DateTimeException e) {
            return Either.ofLeft(new InvalidParametersEvent(Severity.ERROR, "from", "date-parsing exception", e));
        }
    }

    public Either<FEELEvent, TemporalAccessor> apply(@ParameterName( "year" ) Number year, @ParameterName( "month" ) Number month, @ParameterName( "day" ) Number day) {
        if ( year == null ) {
            return Either.ofLeft(new InvalidParametersEvent(Severity.ERROR, "year", "cannot be null"));
        }
        if ( month == null ) {
            return Either.ofLeft(new InvalidParametersEvent(Severity.ERROR, "month", "cannot be null"));
        }
        if ( day == null ) {
            return Either.ofLeft(new InvalidParametersEvent(Severity.ERROR, "day", "cannot be null"));
        }
        
        try {
            return Either.ofRight( LocalDate.of( year.intValue(), month.intValue(), day.intValue() ) );
        } catch (DateTimeException e) {
            return Either.ofLeft(new InvalidParametersEvent(Severity.ERROR, "input parameters date-parsing exception", e));
        }
    }

    public Either<FEELEvent, TemporalAccessor> apply(@ParameterName( "from" ) TemporalAccessor date) {
        if ( date == null ) {
            return Either.ofLeft(new InvalidParametersEvent(Severity.ERROR, "from", "cannot be null"));
        }
        
        try {
            return Either.ofRight( LocalDate.from( LocalDate.from( date ) ) );
        } catch (DateTimeException e) {
            return Either.ofLeft(new InvalidParametersEvent(Severity.ERROR, "from", "date-parsing exception", e));
        }
    }



}
