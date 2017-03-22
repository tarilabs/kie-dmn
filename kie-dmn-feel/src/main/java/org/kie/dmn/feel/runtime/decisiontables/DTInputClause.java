package org.kie.dmn.feel.runtime.decisiontables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.namespace.QName;

import org.kie.dmn.api.feel.runtime.UnaryTest;

public class DTInputClause {
    private final QName typeRef;
    private final String inputExpression;
    private final String inputValuesText;
    private final List<UnaryTest> inputValues;

    public DTInputClause(QName typeRef, String inputExpression, String inputValuesText, List<UnaryTest> inputValues) {
        super();
        this.typeRef = typeRef;
        this.inputExpression = inputExpression;
        this.inputValuesText = inputValuesText;
        if (inputValues != null) {
            this.inputValues = Collections.unmodifiableList(new ArrayList<UnaryTest>(inputValues));
        } else {
            this.inputValues = Collections.emptyList();
        }
    }

    public QName getTypeRef() {
        return typeRef;
    }

    public String getInputExpression() {
        return inputExpression;
    }
    
    public List<UnaryTest> getInputValues() {
        return inputValues;
    }

    public String getInputValuesText() {
        return inputValuesText;
    }
}