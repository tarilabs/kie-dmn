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

package org.kie.dmn.validator;

import org.kie.dmn.feel.FEEL;
import org.kie.dmn.feel.lang.CompiledExpression;
import org.kie.dmn.feel.lang.CompilerContext;
import org.kie.dmn.feel.lang.impl.FEELImpl;
import org.kie.dmn.feel.model.v1_1.Definitions;
import org.kie.dmn.feel.runtime.events.FEELEventListener;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * FEEL expression language engine interface
 *
 * This class is the entry point for the engine use
 */
public interface Validator {

    /**
     * Factory method to create a new FEEL engine instance
     *
     * @return a newly instantiated FEEL engine instance
     */
    static Validator newInstance() {
        return new ValidatorImpl();
    }

    List<Problem> validate(Definitions dmnInput);

    
}
