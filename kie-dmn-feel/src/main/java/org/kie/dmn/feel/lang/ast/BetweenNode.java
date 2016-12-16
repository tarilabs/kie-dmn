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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.antlr.v4.runtime.ParserRuleContext;
import org.kie.dmn.feel.lang.EvaluationContext;
import org.kie.dmn.feel.runtime.events.ASTEventBase;
import org.kie.dmn.feel.runtime.events.FEELEvent;
import org.kie.dmn.feel.runtime.events.FEELEvent.Severity;

public class BetweenNode
        extends BaseNode {

    private BaseNode value;
    private BaseNode start;
    private BaseNode end;

    public BetweenNode(ParserRuleContext ctx, BaseNode value, BaseNode start, BaseNode end) {
        super( ctx );
        this.value = value;
        this.start = start;
        this.end = end;
    }

    public BaseNode getValue() {
        return value;
    }

    public void setValue(BaseNode value) {
        this.value = value;
    }

    public BaseNode getStart() {
        return start;
    }

    public void setStart(BaseNode start) {
        this.start = start;
    }

    public BaseNode getEnd() {
        return end;
    }

    public void setEnd(BaseNode end) {
        this.end = end;
    }

    @Override
    public ASTNodeResult<? extends Object> evaluate(EvaluationContext ctx) {
        List<FEELEvent> problems = new ArrayList<>();
        
        if ( value == null ) problems.add( new ASTEventBase(Severity.ERROR, "Node 'value' is null", this) );
        if ( start == null ) problems.add( new ASTEventBase(Severity.ERROR, "Node 'start' is null", this) );
        if ( end == null )   problems.add( new ASTEventBase(Severity.ERROR, "Node 'end' is null", this) );
        
        if ( problems.size() > 0 ) return ASTNodeResult.ofError(problems);
        
        Comparable val = (Comparable) value.evaluate( ctx ).cata(ps -> { problems.addAll(ps); return null; }, Function.identity() );
        Comparable s = (Comparable) start.evaluate( ctx ).cata(ps -> { problems.addAll(ps); return null; }, Function.identity() );
        Comparable e = (Comparable) end.evaluate( ctx ).cata(ps -> { problems.addAll(ps); return null; }, Function.identity() );
        
        if ( problems.size() > 0 ) return ASTNodeResult.ofError(problems);
        
        if ( !val.getClass().isAssignableFrom( s.getClass() ) ) problems.add( new ASTEventBase(Severity.ERROR, "The 'value' is incompatible with 'start'", this) );
        if ( !val.getClass().isAssignableFrom( e.getClass() ) ) problems.add( new ASTEventBase(Severity.ERROR, "The 'value' is incompatible with 'end'", this) );
        
        if ( problems.size() > 0 ) return ASTNodeResult.ofError(problems);
        
        return ASTNodeResult.ofResult( val.compareTo( s ) >= 0 && val.compareTo( e ) <= 0 );
    }
}
