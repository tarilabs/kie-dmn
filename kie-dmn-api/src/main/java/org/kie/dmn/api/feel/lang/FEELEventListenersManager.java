package org.kie.dmn.api.feel.lang;

import java.util.Set;
import java.util.function.Supplier;

import org.kie.dmn.api.feel.runtime.events.FEELEvent;
import org.kie.dmn.api.feel.runtime.events.FEELEventListener;

public interface FEELEventListenersManager {
    
    void notifyListeners(FEELEvent feelEvent);

    boolean hasListeners();
    
    void addListener(FEELEventListener listener);

    void removeListener(FEELEventListener listener);

    Set<FEELEventListener> getListeners();

    static void notifyListeners(FEELEventListenersManager eventsManager, Supplier<FEELEvent> event) {
        if( eventsManager != null && eventsManager.hasListeners() ) {
            eventsManager.notifyListeners(event.get());
        }
    }
}
