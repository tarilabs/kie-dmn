package org.kie.dmn.validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Problem {
    private Object reference;
    private P problem;
    
    public Problem(P problem, Object reference) {
        this.reference = reference;
        this.problem = problem;
    }

    public Object getReference() {
        return reference;
    }
    
    public String getMessage() {
        return problem.getShortname();
    }

    public P getProblem() {
        return problem;
    }

}
