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

import java.util.Arrays;
import java.util.List;

import org.kie.dmn.feel.runtime.events.FEELEvent;
import org.kie.dmn.feel.runtime.events.InvalidParametersEvent;
import org.kie.dmn.feel.runtime.events.FEELEvent.Severity;
import org.kie.dmn.feel.util.Either;

public class ListOrFunction
        extends BaseFEELFunction {

    public ListOrFunction() {
        super( "list or" );
    }

    public Either<FEELEvent, Boolean> apply(@ParameterName( "list" ) List list) {
        boolean result = false;
        for ( Object element : list ) {
            if ( element instanceof Boolean ) {
                result |= ((Boolean) element);
                if ( result ) {
                    break;
                }
            } else {
                return Either.ofLeft(new InvalidParametersEvent(Severity.ERROR, "an element in the list is not a Boolean"));
            }
        }
        return Either.ofRight( result );
    }

    public Either<FEELEvent, Boolean> apply(@ParameterName( "list" ) Boolean single) {
        return Either.ofRight( single );
    }

    public Either<FEELEvent, Boolean> apply(@ParameterName( "b" ) Object[] list) {
        if ( list == null ) { 
            // Arrays.asList does not accept null as parameter
            return Either.ofLeft(new InvalidParametersEvent(Severity.ERROR, "b", "cannot be null"));
        }
        
        return apply( Arrays.asList( list ) );
    }
}
