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
