package org.kie.dmn.feel.lang.impl;

import org.kie.dmn.feel.lang.Type;

import test.Person;


public class CustomType implements Type {

    private Class<?> wrapped;

    public CustomType(Class<?> class1) {
        this.wrapped = class1;
    }

    @Override
    public String getName() {
        return "Matteo";
    }

    @Override
    public Object fromString(String value) {
        return null;
    }

    @Override
    public String toString(Object value) {
        return null;
    }

    public Class<?> getWrapped() {
        return wrapped;
    }

}
