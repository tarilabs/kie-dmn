package org.kie.dmn.backend.unmarshalling.v1_1.xstream;

import org.kie.dmn.feel.model.v1_1.AuthorityRequirement;
import org.kie.dmn.feel.model.v1_1.DMNElementReference;
import org.kie.dmn.feel.model.v1_1.KnowledgeSource;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class KnowledgeSourceConverter extends DRGElementConverter {
    public static final String LOCATION_URI = "locationURI";

    @Override
    protected void assignChildElement(Object parent, String nodeName, Object child) {
        KnowledgeSource ks = (KnowledgeSource) parent;
        
        if ("authorityRequirement".equals(nodeName)) {
            ks.getAuthorityRequirement().add((AuthorityRequirement) child);
        } else if ("type".equals(nodeName)) {
            ks.setType((String) child);
        } else if ("owner".equals(nodeName)) {
            ks.setOwner((DMNElementReference) child);
        } else { 
            super.assignChildElement(parent, nodeName, child);
        }
    }

    @Override
    protected void assignAttributes(HierarchicalStreamReader reader, Object parent) {
        super.assignAttributes(reader, parent);
        KnowledgeSource ks = (KnowledgeSource) parent;
        
        String locationUri = reader.getAttribute(LOCATION_URI);
        
        ks.setLocationURI(locationUri);
    }

    @Override
    protected void writeChildren(HierarchicalStreamWriter writer, MarshallingContext context, Object parent) {
        super.writeChildren(writer, context, parent);
        KnowledgeSource ks = (KnowledgeSource) parent;
        
        for ( AuthorityRequirement ar : ks.getAuthorityRequirement() ) {
            writeChildrenNode(writer, context, ar);
        }
        if (ks.getType() != null) writeChildrenNode(writer, context, ks.getType());
        if (ks.getOwner() != null) writeChildrenNode(writer, context, ks.getOwner());
    }

    @Override
    protected void writeAttributes(HierarchicalStreamWriter writer, Object parent) {
        super.writeAttributes(writer, parent);
        KnowledgeSource ks = (KnowledgeSource) parent;
        
        if (ks.getLocationURI() != null) writer.addAttribute(LOCATION_URI, ks.getLocationURI());
    }

    public KnowledgeSourceConverter(XStream xstream) {
        super(xstream);
    }
    
    @Override
    protected Object createModelObject() {
        return new KnowledgeSource();
    }

    @Override
    public boolean canConvert(Class clazz) {
        return clazz.equals( KnowledgeSource.class );
    }

}
