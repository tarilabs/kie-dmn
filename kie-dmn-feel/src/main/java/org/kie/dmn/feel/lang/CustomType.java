package org.kie.dmn.feel.lang;

import java.util.List;

public interface CustomType extends Type {

    List<Field> fields();

    public static class Field {
        private final String name;
        private final Type type;
        public Field(String name, Type type) {
            super();
            this.name = name;
            this.type = type;
        }
        
        public String getName() {
            return name;
        }
        
        public Type getType() {
            return type;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Field [name=").append(name).append(", type=").append(type).append("]");
            return builder.toString();
        }
    }
}
