package org.kie.dmn.feel.util;

import java.util.Optional;
import java.util.function.Function;

public class Either<L,R> {
    private final Optional<L> left;
    private final Optional<R> right;
    
    protected Either(Optional<L> left, Optional<R> right) {
      this.left = left;
      this.right = right;
    }

    public static <L,R> Either<L,R> ofLeft(L value) {
        return new Either<>(Optional.of(value), Optional.empty());
    }
    
    public static <L,R> Either<L,R> ofRight(R value) {
        return new Either<>(Optional.empty(), Optional.of(value));
    }
    
    public boolean isLeft() {
        return this.left.isPresent();
    }
    
    public boolean isRight() {
        return ! isLeft();
    }
    
    public <X> X cata(Function<L,X> left, Function<R,X> right) {
        // warning: left.apply, because of FEEL specs, could return null. The below is the safest way to implement cata over this Either.
        return isLeft() ? left.apply( this.left.get() ) : right.apply( this.right.get() );
    }
}