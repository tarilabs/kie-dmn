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

package org.kie.dmn.feel.lang.ast;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.kie.dmn.feel.lang.impl.FEELEventListenersManager;
import org.kie.dmn.feel.runtime.events.FEELEvent;
import org.kie.dmn.feel.util.Either;

public class ASTNodeResult<T> extends Either<List<FEELEvent>, T> {

    protected ASTNodeResult(Optional<List<FEELEvent>> left, Optional<T> right) {
        super(left, right);
    }

    public static <T> ASTNodeResult<T> ofError(FEELEvent event) {
        return new ASTNodeResult<>(Optional.of(Arrays.asList(event)), Optional.empty());
    }
    
    public static <T> ASTNodeResult<T> ofError(List<FEELEvent> events) {
        return new ASTNodeResult<>(Optional.of(events), Optional.empty());
    }
    
    public static <T> ASTNodeResult<T> ofResult(T value) {
        return new ASTNodeResult<>(Optional.empty(), Optional.of(value));
    }
    
    public T valueOrNotifyThenNull(FEELEventListenersManager eventsManager) {
        return this.cata( (left) -> { left.forEach( evt -> FEELEventListenersManager.notifyListeners( eventsManager, () -> evt ));
                                      return null;
                            }, Function.identity() );
    }
}