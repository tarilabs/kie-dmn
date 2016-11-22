package org.kie.dmn.feel.lang.types;

import java.util.Arrays;
import java.util.List;

import org.kie.dmn.feel.lang.Scope;
import org.kie.dmn.feel.lang.Type;

public class CustomTypeSymbol extends BaseSymbol {
    public CustomTypeSymbol() {
        super();
    }

    public CustomTypeSymbol(String id, Scope scope) {
        super(id, scope);
    }

    public CustomTypeSymbol(String id, Type type, Scope scope) {
        super(id, type, scope);
    }

    public CustomTypeSymbol(String id, Type type) {
        super(id, type);
    }

    public CustomTypeSymbol(String id) {
        super(id);
    }

    public List<Field> fields() {
        return Arrays.asList( new Field[]{ new Field("first name", String.class), new Field("last name", String.class)  } );
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
