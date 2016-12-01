package org.kie.dmn.feel.runtime.functions;

import java.util.Optional;

import org.kie.dmn.feel.runtime.events.FEELEvent;
import org.kie.dmn.feel.util.Either;

public class FEELFnResult<T> extends Either<FEELEvent, T> {

    protected FEELFnResult(Optional<FEELEvent> left, Optional<T> right) {
        super(left, right);
    }

    public static <T> FEELFnResult<T> ofError(FEELEvent event) {
        return new FEELFnResult<>(Optional.of(event), Optional.empty());
    }
    
    public static <T> FEELFnResult<T> ofResult(T value) {
        return new FEELFnResult<>(Optional.empty(), Optional.of(value));
    }
    
}