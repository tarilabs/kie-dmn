package org.kie.dmn.backend.unmarshalling.v1_1.xstream;

import org.kie.dmn.feel.model.v1_1.AuthorityRequirement;
import org.kie.dmn.feel.model.v1_1.DMNElementReference;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class AuthorityRequirementConverter extends DMNModelInstrumentedBaseConverter {

    @Override
    protected void assignChildElement(Object parent, String nodeName, Object child) {
        AuthorityRequirement ar = (AuthorityRequirement) parent;
        
        if ("requiredDecision".equals(nodeName)) {
            ar.setRequiredDecision( (DMNElementReference) child );
        } else if ("requiredInput".equals(nodeName)) {
            ar.setRequiredInput( (DMNElementReference) child );
        } else if ("requiredAuthority".equals(nodeName)) {
            ar.setRequiredAuthority( (DMNElementReference) child );
        } else {
            super.assignChildElement(parent, nodeName, child);
        }
    }

    @Override
    protected void assignAttributes(HierarchicalStreamReader reader, Object parent) {
        super.assignAttributes(reader, parent);
        
        // no attributes.
    }

    @Override
    protected void writeChildren(HierarchicalStreamWriter writer, MarshallingContext context, Object parent) {
        super.writeChildren(writer, context, parent);
        AuthorityRequirement ar = (AuthorityRequirement) parent;
        
        if (ar.getRequiredDecision() != null) writeChildrenNode(writer, context, ar.getRequiredDecision()); 
        // TODO or if else?
        if (ar.getRequiredInput() != null) writeChildrenNode(writer, context, ar.getRequiredInput());
        if (ar.getRequiredAuthority() != null) writeChildrenNode(writer, context, ar.getRequiredAuthority());
    }

    @Override
    protected void writeAttributes(HierarchicalStreamWriter writer, Object parent) {
        super.writeAttributes(writer, parent);

        // no attributes.
    }

    public AuthorityRequirementConverter(XStream xstream) {
        super(xstream);
    }

    @Override
    protected Object createModelObject() {
        return new AuthorityRequirement();
    }

    @Override
    public boolean canConvert(Class clazz) {
        return clazz.equals( AuthorityRequirement.class );
    }
}
