package org.kie.dmn.feel.lang.impl;

import org.kie.dmn.feel.lang.Type;


public class CustomType implements Type {

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

}
