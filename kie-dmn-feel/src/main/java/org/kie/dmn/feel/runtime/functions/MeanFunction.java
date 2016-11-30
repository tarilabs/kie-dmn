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

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.kie.dmn.feel.runtime.events.FEELEvent;
import org.kie.dmn.feel.runtime.events.InvalidParametersEvent;
import org.kie.dmn.feel.runtime.events.FEELEvent.Severity;
import org.kie.dmn.feel.util.Either;
import org.kie.dmn.feel.util.EvalHelper;

public class MeanFunction
        extends BaseFEELFunction {

    private SumFunction sum = new SumFunction();

    public MeanFunction() {
        super( "mean" );
    }

    public Either<FEELEvent, BigDecimal> apply(@ParameterName( "list" ) List list) {
        Either<FEELEvent, BigDecimal> s = sum.apply( list );
        
        Function<FEELEvent, Either<FEELEvent, BigDecimal>> ifLeft = (event) -> {
            return Either.ofLeft(new InvalidParametersEvent(Severity.ERROR, "list", "unable to sum the elements which is required to calculate the mean"));
        };
        
        Function<BigDecimal, Either<FEELEvent, BigDecimal>> ifRight = (sum) -> {
            try {
                return Either.ofRight( sum.divide( BigDecimal.valueOf( list.size() ), MathContext.DECIMAL128 ) );
            } catch (Exception e) {
                return Either.ofLeft( new InvalidParametersEvent(Severity.ERROR, "unable to perform division to calculate the mean", e) );
            }
        };
        
        return s.cata(ifLeft, ifRight);
    }

    public Either<FEELEvent, BigDecimal> apply(@ParameterName( "list" ) Number single) {
        if ( single == null ) { 
            // Arrays.asList does not accept null as parameter
            return Either.ofLeft(new InvalidParametersEvent(Severity.ERROR, "list", "the single value list cannot be null"));
        }
        
        if( single instanceof BigDecimal ) {
            return Either.ofRight((BigDecimal) single );
        } 
        BigDecimal result = EvalHelper.getBigDecimalOrNull( single );
        if ( result != null ) {
            return Either.ofRight( result );
        } else {
            return Either.ofLeft(new InvalidParametersEvent(Severity.ERROR, "list", "single element in list not a number"));
        }
    }

    public Either<FEELEvent, BigDecimal> apply(@ParameterName( "n" ) Object[] list) {
        if ( list == null ) { 
            // Arrays.asList does not accept null as parameter
            return Either.ofLeft(new InvalidParametersEvent(Severity.ERROR, "n", "cannot be null"));
        }
        
        return apply( Arrays.asList( list ) );
    }
}
