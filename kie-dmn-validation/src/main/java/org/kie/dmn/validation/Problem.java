package org.kie.dmn.validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Problem {
    private Object subject;
    private P p;
    
    public Problem(Object subject, P p) {
        super();
        this.subject = subject;
        this.p = p;
    }

    
    public Object getSubject() {
        return subject;
    }

    
    public String getMessage() {
        return p.getShortname();
    }


    
    public P getP() {
        return p;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Problem [subject=").append(subject)
        .append(", message=").append(getMessage())
        .append(", p=").append(p)
        .append("]");
        return builder.toString();
    }

    
}
