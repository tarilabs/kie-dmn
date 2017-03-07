package org.kie.dmn.core.util;

import org.kie.dmn.feel.util.Msg.Message;
import org.kie.dmn.feel.util.Msg.Message0;
import org.kie.dmn.feel.util.Msg.Message1;
import org.kie.dmn.feel.util.Msg.Message2;
import org.kie.dmn.feel.util.Msg.Message3;
import org.kie.dmn.feel.util.Msg.Message4;

public class Msg {
    public static final Message2 ELEMENT_WITH_ID_NOT_SUPPORTED = new Message2("Element %s with id='%s' not supported.");
    public static final Message2 REQ_INPUT_NOT_FOUND_FOR_NODE = new Message2("Required input '%s' not found for node '%s'");
    public static final Message2 REQ_DECISION_NOT_FOUND_FOR_NODE = new Message2("Required Decision '%s' not found for node '%s'");
    public static final Message2 REQ_BKM_NOT_FOUND_FOR_NODE = new Message2("Required Business Knowledge Model '%s' not found for node '%s'");
    public static final Message2 UNKNOWN_TYPE_REF_ON_NODE = new Message2("Unknown type reference '%s' on node '%s'");
    public static final Message2 NO_TYPE_DEF_FOUND_FOR_NODE = new Message2("No '$s' type definition found for node '%s'");
    public static final Message3 NO_TYPE_DEF_FOUND_FOR_ELEMENT_ON_NODE = new Message3("No '$s' type definition found for element '$s' on node '$s'");
    public static final Message2 INVALID_NAME_VARIABLENAME = new Message2("Invalid name '$s': $s");
    public static final Message1 INVALID_SYNTAX = new Message1("$s: invalid syntax");
    public static final Message1 DECISION_NOT_FOUND_FOR_NAME = new Message1("Decision not found for name '$s'");
    public static final Message1 DECISION_NOT_FOUND_FOR_ID = new Message1("Decision not found for id '$s'");
    public static final Message1 MISSING_EXPRESSION_FOR_BKM_NODE_SKIP_EVAL = new Message1("Missing expression for Business Knowledge Model node '$s'. Skipping evaluation.");
    public static final Message2 MISSING_DEP_FOR_BKM = new Message2("Missing dependency for Business Knowledge Model node '$s': dependency='$s'");
    public static final Message2 ERROR_EVAL_BKM_NODE = new Message2("Error evaluating Business Knowledge Model node '$s': $s" );
    public static final Message1 MISSING_EXPRESSION_FOR_DECISION_NODE_SKIP_EVAL = new Message1("Missing expression for Decision node '$s'. Skipping evaluation.");
    public static final Message2 MISSING_DEP_FOR_DECISION = new Message2("Missing dependency for Decision node '$s': dependency='$s'");
    public static final Message2 ERROR_EVAL_DECISION_NODE = new Message2("Error evaluating Decision node '$s': $s" );
    public static final Message2 UNABLE_TO_EVALUATE_DECISION_AS_IT_DEPS = new Message2("Unable to evaluate decision '$s' as it depends on decision '$s'");
    public static final Message2 EXPR_TYPE_NOT_SUPPORTED_IN_NODE = new Message2("Expression type '$s' not supported in node '$s'");
    public static final Message1 NO_EXPR_DEF_FOR_NODE = new Message1("No expression defined for node '$s'");
    public static final Message3 ERR_COMPILING_FEEL_EXPR_ON_DT_INPUT_CLAUSE_IDX = new Message3("Error compiling FEEL expression '$s' on decision table '$s', input clause #$s");
    public static final Message3 ERR_COMPILING_FEEL_EXPR_ON_DT_OUTPUT_CLAUSE_IDX = new Message3("Error compiling FEEL expression '$s' on decision table '$s', output clause #$s");
    public static final Message3 ERR_COMPILING_FEEL_EXPR_ON_DT_RULE_IDX = new Message3("Error compiling FEEL expression '$s' on decision table '$s', rule #$s");
    public static final Message2 ERR_COMPILING_FEEL_EXPR_ON_DT = new Message2("Error compiling FEEL expression '$s' on decision table '$s'");
    public static final Message2 ERR_COMPILING_ALLOWED_VALUES_LIST_ON_ITEM_DEF = new Message2("Error compiling allowed values list '$s' on item definition '$s'");
    public static final Message3 ERR_COMPILING_FEEL_EXPR_FOR_NAME_ON_NODE = new Message3("Error compiling FEEL expression '$s' for name '$s' on node '$s'");
    
    public static String createMessage( Message0 message) {
        return Msg.buildMessage(message);
    }
    public static String createMessage( Message1 message, Object p1) {
        return Msg.buildMessage(message, p1);
    }
    public static String createMessage( Message2 message, Object p1, Object p2) {
        return Msg.buildMessage(message, p1, p2);
    }
    public static String createMessage( Message3 message, Object p1, Object p2, Object p3) {
        return Msg.buildMessage(message, p1, p2, p3);
    }
    public static String createMessage( Message4 message, Object p1, Object p2, Object p3, Object p4) {
        return Msg.buildMessage(message, p1, p2, p3, p4);
    }
    
    private static String buildMessage( Message message, Object... params ) {
        return String.format( message.getMask(), params );
    }
}
