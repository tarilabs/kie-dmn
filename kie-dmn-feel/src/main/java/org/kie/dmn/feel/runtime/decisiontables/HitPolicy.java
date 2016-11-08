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

package org.kie.dmn.feel.runtime.decisiontables;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.kie.dmn.feel.model.v1_1.DecisionRule;

public enum HitPolicy {
    UNIQUE( "U", "UNIQUE", HitPolicy::unique ),
    FIRST( "F", "FIRST", HitPolicy::first ),
    PRIORITY( "P", "PRIORITY", HitPolicy::priority ),
    ANY( "A", "ANY", HitPolicy::any ),
    COLLECT( "C", "COLLECT", HitPolicy::ruleOrder ),    // Collect – return a list of the outputs in arbitrary order 
    COLLECT_SUM( "C+", "COLLECT SUM" ),
    COLLECT_COUNT( "C#", "COLLECT COUNT" ),
    COLLECT_MIN( "C<", "COLLECT MIN" ),
    COLLECT_MAX( "C>", "COLLECT MAX" ),
    RULE_ORDER( "R", "RULE ORDER", HitPolicy::ruleOrder ),
    OUTPUT_ORDER( "O", "OUTPUT ORDER", HitPolicy::outputOrder );

    private final String shortName;
    private final String longName;
    private final HitPolicyDTI dti;

    HitPolicy(final String shortName, final String longName) {
        this.shortName = shortName;
        this.longName = longName;
        this.dti = HitPolicy::notImplemented;
    }
    
    HitPolicy(final String shortName, final String longName, final HitPolicyDTI dti) {
        this.shortName = shortName;
        this.longName = longName;
        this.dti = dti;
    }
    
    @FunctionalInterface
    public static interface HitPolicyDTI {
        Object dti(Object[] params, List<DTDecisionRule> decisionRules, List<DTInputClause> inputs, List<DTOutputClause> outputs);
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }
    
    public HitPolicyDTI getDti() {
        return dti;
    }

    public static HitPolicy fromString(String policy) {
        policy = policy.toUpperCase();
        for ( HitPolicy c : HitPolicy.values() ) {
            if ( c.shortName.equals( policy ) || c.longName.equals( policy ) ) {
                return c;
            }
        }
        throw new IllegalArgumentException( "Unknown hit policy: " + policy );
    }
    
    public static Object notImplemented(Object[] params, List<DTDecisionRule> decisionRules, List<DTInputClause> inputs, List<DTOutputClause> outputs) {
        throw new RuntimeException("Not implemented");
    }
    
    public static List<DTDecisionRule> matchingDecisionRules(Object[] params, List<DTDecisionRule> decisionRules, List<DTInputClause> inputs) {
        List<DTDecisionRule> matchingDecisionRules = new ArrayList<>();
        for ( DTDecisionRule decisionRule : decisionRules ) {
            if ( DTInvokerFunction.match(params, decisionRule, inputs) ) {
                matchingDecisionRules.add( decisionRule );
            }
        }
        return matchingDecisionRules;
    }
    
    /**
     *  Each hit results in one output value (multiple outputs are collected into a single context value)
     */
    public static Object hitToOutput(DTDecisionRule hit) {
        List<Object> outputEntry = hit.getOutputEntry();
        if ( outputEntry.size() == 1 ) {
            return outputEntry.get( 0 );
        } else {
            return outputEntry;
        }
    }
    
    /**
     * Unique – only a single rule can be matched
     */
    public static Object unique(Object[] params, List<DTDecisionRule> decisionRules, List<DTInputClause> inputs, List<DTOutputClause> outputs) {
        List<DTDecisionRule> matchingDecisionRules = matchingDecisionRules(params, decisionRules, inputs);
        
        if ( matchingDecisionRules.size() > 1 ) {
            throw new RuntimeException("only a single rule can be matched");
        }
            
        if ( matchingDecisionRules.size() == 1 ) {
            return hitToOutput( matchingDecisionRules.get(0) );
        }
        
        return null;
    }
    
    /**
     * First – return the first match in rule order 
     */
    public static Object first(Object[] params, List<DTDecisionRule> decisionRules, List<DTInputClause> inputs, List<DTOutputClause> outputs) {
        List<DTDecisionRule> matchingDecisionRules = matchingDecisionRules(params, decisionRules, inputs);
            
        if ( matchingDecisionRules.size() >= 1 ) {
            return hitToOutput( matchingDecisionRules.get(0) );
        }
        
        return null;
    }
    
    /**
     * Any – multiple rules can match, but they all have the same output
     */
    public static Object any(Object[] params, List<DTDecisionRule> decisionRules, List<DTInputClause> inputs, List<DTOutputClause> outputs) {
        List<DTDecisionRule> matchingDecisionRules = matchingDecisionRules(params, decisionRules, inputs);  
        
        if ( matchingDecisionRules.size() > 1 ) {
            // TODO revise.
            long distinctOutputEntry = matchingDecisionRules.stream()
                .map( dr -> dr.getOutputEntry() )
                .distinct()
                .count();
            if ( distinctOutputEntry > 1 ) {
                throw new RuntimeException("multiple rules can match, but they [must] all have the same output");    
            }
        }
            
        if ( matchingDecisionRules.size() >= 1 ) {
            return hitToOutput( matchingDecisionRules.get(0) );
        }
        
        return null;
    }
    
    /**
     * Priority – multiple rules can match, with different outputs. The output that comes first in the supplied output values list is returned
     * TODO what about if the set of {different outputs} is not contained at all in the set of {the supplied output values} ?
     * TODO I think there is conflict in specs between hitpolicy Priority as defined in FEEL Vs the broader DMN scope
     *      in the FEEL scope, is ok.
     *      in the broader DMN scope it reads:
     *      "Priority: multiple rules can match, with different output entries. This policy returns the matching rule with the
 highest output priority."
            WHAT-IF in the broader DMN scope I have 2+ outputs, and I define outputValueList in such a way to make "conflicting" priority between the *matching rules*
            instead of the granularity of the single output? 
     */
    public static Object priority(Object[] params, List<DTDecisionRule> decisionRules, List<DTInputClause> inputs, List<DTOutputClause> outputs) {
        List<DTDecisionRule> matchingDecisionRules = matchingDecisionRules(params, decisionRules, inputs);  
        
        List<Object> resultOrdered = new ArrayList<>();
        for ( int i = 0; i < outputs.size(); i++ ) {
            final int outIndex = i;
            DTOutputClause out = outputs.get( outIndex );
            for ( String outValue : out.getOutputValues() ) {
                boolean inMatchedRules = matchingDecisionRules.stream()
                    .map( dr -> dr.getOutputEntry().get( outIndex ) )
                    .anyMatch( outN -> outN.equals( outValue ) );
                if ( inMatchedRules ) {
                    resultOrdered.add(outValue);
                    break; // outValue found, now move fwd to the next outputs[i]
                }
            }
        }
        
        return resultOrdered;
    }

    /**
     * Output order – return a list of outputs in the order of the output values list 
     */
    public static Object outputOrder(Object[] params, List<DTDecisionRule> decisionRules, List<DTInputClause> inputs, List<DTOutputClause> outputs) {
        List<DTDecisionRule> matchingDecisionRules = matchingDecisionRules(params, decisionRules, inputs);  
        
        List<Object> resultOrdered = new ArrayList<>();
        for ( int i = 0; i < outputs.size(); i++ ) {
            final int outIndex = i;
            DTOutputClause out = outputs.get( outIndex );
            for ( String outValue : out.getOutputValues() ) {
                boolean inMatchedRules = matchingDecisionRules.stream()
                    .map( dr -> dr.getOutputEntry().get( outIndex ) )
                    .anyMatch( outN -> outN.equals( outValue ) );
                if ( inMatchedRules ) {
                    resultOrdered.add(outValue);
                    // similar to hitpolicy "priority" but in this case I continue to evaluate all elements of .getOutputValues() ..
                }
            }
        }
        
        return resultOrdered;
    }
    
    /**
     * Rule order – return a list of outputs in rule order
     * Collect – return a list of the outputs in arbitrary order 
     */
    public static Object ruleOrder(Object[] params, List<DTDecisionRule> decisionRules, List<DTInputClause> inputs, List<DTOutputClause> outputs) {
        return matchingDecisionRules(params, decisionRules, inputs).stream()
                .map( HitPolicy::hitToOutput )
                .collect( Collectors.toList() );
    }
}
