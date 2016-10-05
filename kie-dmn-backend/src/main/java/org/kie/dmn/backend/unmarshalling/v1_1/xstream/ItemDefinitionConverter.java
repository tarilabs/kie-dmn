package org.kie.dmn.backend.unmarshalling.v1_1.xstream;

import org.kie.dmn.feel.model.v1_1.ItemDefinition;
import org.kie.dmn.feel.model.v1_1.QName;
import org.kie.dmn.feel.model.v1_1.UnaryTests;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class ItemDefinitionConverter extends NamedElementConverter {
    public static final String TYPE_LANGUAGE = "typeLanguage";
    public static final String IS_COLLECTION = "isCollection";
    
    @Override
    protected void assignChildElement(Object parent, String nodeName, Object child) {
        ItemDefinition id = (ItemDefinition) parent;
        
        if ("typeRef".equals(nodeName)) {
            id.setTypeRef((QName) child);
        } else if ("allowedValues".equals(nodeName)) {
            id.setAllowedValues((UnaryTests) child);
        } else if ("itemComponent".equals(nodeName)) {
            id.getItemComponent().add((ItemDefinition) child);
        } else {
            super.assignChildElement(parent, nodeName, child);
        }
    }

    @Override
    protected void assignAttributes(HierarchicalStreamReader reader, Object parent) {
        super.assignAttributes(reader, parent);
        ItemDefinition id = (ItemDefinition) parent;
        
        String typeLanguage = reader.getAttribute(TYPE_LANGUAGE);
        String isCollectionValue = reader.getAttribute(IS_COLLECTION);
        
        id.setTypeLanguage(typeLanguage);
        id.setIsCollection(Boolean.valueOf(isCollectionValue));
    }

    @Override
    protected void writeChildren(HierarchicalStreamWriter writer, MarshallingContext context, Object parent) {
        super.writeChildren(writer, context, parent);
        ItemDefinition id = (ItemDefinition) parent;
        
        if (id.getTypeRef() != null) writeChildrenNode(writer, context, id.getTypeRef());
        if (id.getAllowedValues() != null) writeChildrenNode(writer, context, id.getAllowedValues());
        for ( ItemDefinition ic : id.getItemComponent() ) {
            writeChildrenNode(writer, context, ic);
        }
    }

    @Override
    protected void writeAttributes(HierarchicalStreamWriter writer, Object parent) {
        super.writeAttributes(writer, parent);
        ItemDefinition id = (ItemDefinition) parent;
        
        if (id.getTypeLanguage() != null) writer.addAttribute(TYPE_LANGUAGE, id.getTypeLanguage());
        writer.addAttribute(IS_COLLECTION, Boolean.valueOf(id.isIsCollection()).toString());
    }

    public ItemDefinitionConverter(XStream xstream) {
        super(xstream);
    }

    @Override
    protected Object createModelObject() {
        return new ItemDefinition();
    }

    @Override
    public boolean canConvert(Class clazz) {
        return clazz.equals( ItemDefinition.class );
    }

}
