package org.kie.dmn.feel.lang;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;


@Retention(RUNTIME)
public @interface FEELAccessor {

    String value();

}
