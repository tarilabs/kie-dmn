package org.kie.dmn.feel.lang.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.kie.dmn.feel.lang.CustomType;
import org.kie.dmn.feel.lang.FEELAccessor;
import org.kie.dmn.feel.lang.Type;

public class MapBackedType implements CustomType {
    
    private List<Field> fields = new ArrayList<>();
    
    public MapBackedType() {
    }

    /**
     * Utility constructor by reflection over key-value pairs.
     * @param fields
     */
    public MapBackedType(Map<String, ?> map) {
        map.entrySet().stream()
            .map( kv -> new Field( kv.getKey(), Type.determineTypeFromClass( kv.getValue().getClass()) ) )
            .forEach( f -> fields.add(f) );
    }
    
    public MapBackedType(List<Field> fields) {
        this.fields.addAll(fields);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Object fromString(String value) {
        return null;
    }

    @Override
    public String toString(Object value) {
        return null;
    }
    
    @Override
    public List<Field> fields() {
        return fields;
    }
    
    public MapBackedType addField(String name, Type type) {
        fields.add(new Field(name, type));
        return this;
    }
}
