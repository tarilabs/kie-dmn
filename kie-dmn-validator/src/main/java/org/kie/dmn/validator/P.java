package org.kie.dmn.validator;


public enum P {
    FAILED_XML_VALIDATION("Failed XML validation of DMN file"),
    ITEMDEF_NOT_UNIQUE("itemDefinition name is not unique in the model"),
    ITEMCOMP_DUPLICATED("itemComponent with duplicated name within a same parent itemDefinition"),
    DECISION_MISSING_VAR("Decision is missing variable or name does not matche the variable name"),
    DECISION_MISSING_EXPR("Decision is missing a value expression"),
    BKM_MISSING_VAR("BKM is missing variable or name does not matche the variable name"),
    BKM_MISSING_EXPR("BKM missing a value expression"),
    FORMAL_PARAM_DUPLICATED("formal parameter with duplicated name"),
    DRGELEM_NOT_UNIQUE("DRGElement(BKM | Decision | InputData | KnowledgeSource) name not unique in the model"),
    INPUTDATA_MISSING_VAR("InputData is missing variable or name does not matche the variable name"),
    INVOCATION_MISSING_TARGET("Invocation referencing a DRGElement target not found"),
    INVOCATION_WRONG_PARAM_COUNT("Invocation referecing a DRGElement but number of parameters is not consistent with target"),
    INVOCATION_INCONSISTENT_PARAM_NAMES("Invocation Binding parameter names SHALL be a subset of the formalParameters of the calledFunction"),
    CONTEXT_DUP_ENTRY("Context contains duplicate context entry keys"),
    CONTEXT_ENTRY_NOTYPEREF("Context entry is missing typeRef"),
    RELATION_DUP_COLUMN("Relation contains duplicate column name"),
    RELATION_ROW_CELL_NOTLITERAL("Relation contains a row with a cell which is not a literalExpression"),
    RELATION_ROW_CELLCOUNTMISMATCH("Relation contains a row with wrong number of cells"),
    DTABLE_SINGLEOUT_NONAME("Decision table with single output should not have output name"),
    DTABLE_SINGLEOUT_NOTYPEREF("Decision table with single output should not have output typeRef"),
    DTABLE_MULTIPLEOUT_NAME("Decision table with multiple output should have output name"),
    DTABLE_MULTIPLEOUT_TYPEREF("Decision table with multiple output should have output typeRef"),
    DTABLE_PRIORITY_MISSING_OUTVALS("Decision table with Priority as hit policy requires output to specify output values")
    ;

    private String shortname;
    
    P(String shortname) {
        this.shortname = shortname;
    }

    
    public String getShortname() {
        return shortname;
    }
    
    
}
