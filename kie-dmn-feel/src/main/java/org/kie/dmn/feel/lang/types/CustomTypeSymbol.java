package org.kie.dmn.feel.lang.types;

import java.beans.MethodDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.kie.dmn.feel.lang.FEELAccessor;
import org.kie.dmn.feel.lang.Scope;
import org.kie.dmn.feel.lang.Type;
import org.kie.dmn.feel.lang.impl.CustomType;

public class CustomTypeSymbol extends BaseSymbol {
    
    private List<Field> fields = new ArrayList<>();

    public CustomTypeSymbol(String id, Type type) {
        super(id, type);
        if (type instanceof CustomType) {
            CustomType customType = (CustomType) type;
            Stream.of( customType.getWrapped().getMethods() )
                .filter( m -> m.getAnnotation(FEELAccessor.class) != null )
                .forEach( m -> fields.add( new Field( m.getAnnotation(FEELAccessor.class).value() , m.getReturnType()) ) );
                ;
        }
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
