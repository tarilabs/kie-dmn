package org.kie.dmn.feel.lang.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kie.dmn.feel.lang.CustomType;
import org.kie.dmn.feel.lang.Type;
import org.kie.dmn.feel.parser.feel11.ParserHelper;

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
            .map( kv -> new Field( kv.getKey(), ParserHelper.determineTypeFromClass( kv.getValue().getClass()) ) )
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
