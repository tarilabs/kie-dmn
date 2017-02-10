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

package org.kie.dmn.api.core;

import org.kie.dmn.api.core.DMNMessage.Severity;
import org.kie.dmn.api.feel.runtime.events.FEELEvent;

public interface InternalDMNResult extends DMNResult {

    void setContext(DMNContext context);

    void addMessage(DMNMessage msg);

    DMNMessage addMessage(Severity severity, String message, String sourceId);

    DMNMessage addMessage(Severity severity, String message, String sourceId, Throwable exception);

    void addMessage(Severity severity, String message, String sourceId, FEELEvent feelEvent);

}
