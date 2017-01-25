package org.kie.dmn.validation;

public class ValidationMsg {
    public static enum Severity {
        TRACE, INFO, WARN, ERROR;
    }
    private Object reference;
    private Msg message;
    private Severity severity;
    
    public ValidationMsg(Severity severity, Msg message, Object reference) {
        this.severity = severity;
        this.message = message;
        this.reference = reference;
    }

    public Object getReference() {
        return reference;
    }
    
    public String getShortname() {
        return message.getShortname();
    }

    public Msg getMessage() {
        return message;
    }

    public Severity getSeverity() {
        return this.severity;
    }
}
