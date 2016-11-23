package org.kie.dmn.feel.lang;

import java.util.List;

public interface CustomType extends Type {

    List<Field> fields();

    public static class Field {
        private final String name;
        private final Class<?> type;
        public Field(String name, Class<?> type) {
            super();
            this.name = name;
            this.type = type;
        }
        
        public String getName() {
            return name;
        }
        
        public Class<?> getType() {
            return type;
        }
        
    }
}
