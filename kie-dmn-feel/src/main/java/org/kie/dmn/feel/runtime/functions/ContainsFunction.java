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

import org.kie.dmn.feel.runtime.events.FEELEvent;
import org.kie.dmn.feel.runtime.events.InvalidParametersEvent;
import org.kie.dmn.feel.runtime.events.FEELEvent.Severity;
import org.kie.dmn.feel.util.Either;

public class ContainsFunction
        extends BaseFEELFunction {

    public ContainsFunction() {
        super( "contains" );
    }

    public Either<FEELEvent, Boolean> apply(@ParameterName("string") String string, @ParameterName("match") String match) {
        if ( string == null ) {
            return Either.ofLeft(new InvalidParametersEvent(Severity.ERROR, "string", "cannot be null"));
        }
        if ( match == null ) {
            return Either.ofLeft(new InvalidParametersEvent(Severity.ERROR, "match", "cannot be null"));
        } else {
            return Either.ofRight( string.indexOf( match ) >= 0 );
        }
    }

}
