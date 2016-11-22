package org.kie.dmn.feel.lang.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.kie.dmn.feel.lang.FEELAccessor;
import org.kie.dmn.feel.lang.Type;

public class CustomType implements Type {
    
    private List<Field> fields = new ArrayList<>();
    private Class<?> wrapped;

    public CustomType(Class<?> class1) {
        this.wrapped = class1;
        Stream.of( class1.getMethods() )
            .filter( m -> m.getAnnotation(FEELAccessor.class) != null )
            .forEach( m -> fields.add( new Field( m.getAnnotation(FEELAccessor.class).value() , m.getReturnType()) ) );
            ;
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
    
    public List<Field> fields() {
        return fields;
    }

    public static class Field {
        public final String name;
        public final Class<?> type;
        public Field(String name, Class<?> type) {
            super();
            this.name = name;
            this.type = type;
        }
    }
}
