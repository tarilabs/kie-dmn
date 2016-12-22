package org.kie.dmn.validator;


public enum P {
    ITEMDEF_NOT_UNIQUE("itemDefinition name is not unique in the model"),
    ITEMCOMP_DUPLICATED("itemComponent with duplicated name within a same parent itemDefinition"),
    DECISION_MISSING_VAR("Decision is missing variable or name does not matche the variable name"),
    DECISION_MISSING_EXPR("Decision is missing a value expression"),
    BKM_MISSING_VAR("BKM is missing variable or name does not matche the variable name"),
    BKM_MISSING_EXPR("BKM missing a value expression"),
    FORMAL_PARAM_DUPLICATED("formal parameter with duplicated name"),
    DRGELEM_NOT_UNIQUE("DRGElement(BKM | Decision | InputData | KnowledgeSource) name not unique in the model"),
    INPUTDATA_MISSING_VAR("InputData is missing variable or name does not matche the variable name")
    ;

    private String shortname;
    
    P(String shortname) {
        this.shortname = shortname;
    }

    
    public String getShortname() {
        return shortname;
    }
    
    
}
