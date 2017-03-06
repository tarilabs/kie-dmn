package org.kie.dmn.core.util;


public class Msg extends org.kie.dmn.feel.util.Msg {
    public static final Message2 ELEMENT_WITH_ID_NOT_SUPPORTED = new Message2("Element %s with id='%s' not supported.");
    public static final Message2 REQ_INPUT_NOT_FOUND_FOR_NODE = new Message2("Required input '%s' not found for node '%s'");
    public static final Message2 REQ_DECISION_NOT_FOUND_FOR_NODE = new Message2("Required Decision '%s' not found for node '%s'");
    public static final Message2 REQ_BKM_NOT_FOUND_FOR_NODE = new Message2("Required Business Knowledge Model '%s' not found for node '%s'");
    public static final Message2 UNKNOWN_TYPE_REF_ON_NODE = new Message2("Unknown type reference '%s' on node '%s'");
    public static final Message2 NO_TYPE_DEF_FOUND_FOR_NODE = new Message2("No '$s' type definition found for node '%s'");
    public static final Message3 NO_TYPE_DEF_FOUND_FOR_ELEMENT_ON_NODE = new Message3("No '$s' type definition found for element '$s' on node '$s'");
}
