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

package org.kie.dmn.feel.lang.impl;

import org.kie.dmn.feel.lang.CompiledExpression;
import org.kie.dmn.feel.lang.ast.ASTNode;
import org.kie.dmn.feel.lang.types.SymbolTable;
import org.kie.dmn.feel.runtime.events.InvalidParametersEvent;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompiledExpressionImpl implements CompiledExpression {
    private ASTNode     expression;

    public CompiledExpressionImpl(ASTNode expression) {
        this.expression = expression;
    }

    public ASTNode getExpression() {
        return expression;
    }

    public Object evaluate(FEELEventListenersManager eventsManager, Map<String, Object> inputVariables) {
        EvaluationContextImpl ctx = new EvaluationContextImpl( eventsManager );
        inputVariables.entrySet().stream().forEach( e -> ctx.setValue( e.getKey(), e.getValue() ) );
        // TODO REVIEW: Here I need to wrap with the EventsManager error dispatch.
        Object eitherResult = expression.evaluate( ctx ).cata( (left) -> { 
            left.forEach( evt -> {
                FEELEventListenersManager.notifyListeners( eventsManager, () -> evt );
            });
            return null;
        }, Function.identity() );
        return eitherResult;
    }

    @Override
    public String toString() {
        return "CompiledExpressionImpl{" +
               "expression=" + expression +
               '}';
    }
}
