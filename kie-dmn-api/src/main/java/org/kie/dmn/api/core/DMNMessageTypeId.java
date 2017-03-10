package org.kie.dmn.api.core;

public enum DMNMessageTypeId {
    ELEMENT_WITH_ID_NOT_SUPPORTED(Tag.DMN_CORE),                // compilation
    REQ_NOT_FOUND(Tag.DMN_CORE),                                // compilation
    NO_TYPE_REF_FOUND(Tag.DMN_CORE),                            // dups?
    NO_TYPE_DEF_FOUND(Tag.DMN_CORE),                            // dups?
    INVALID_NAME(Tag.DMN_CORE),                                 // dups?
    INVALID_SYNTAX(Tag.DMN_CORE),                               // FEEL runtime
    DECISION_NOT_FOUND(Tag.DMN_CORE),                           // API-call related
    MISSING_EXPRESSION(Tag.DMN_CORE),                           // dups?
    MISSING_DEP(Tag.DMN_CORE),                                  // compilation
    ERROR_EVAL_NODE(Tag.DMN_CORE),                              // runtime
    EXPR_TYPE_NOT_SUPPORTED_IN_NODE(Tag.DMN_CORE),              // compilation
    NO_EXPR_DEF_FOR_NODE(Tag.DMN_CORE),                         // is this a DUPS of MISSING_EXPRESSION ?
    ERR_COMPILING_FEEL(Tag.DMN_CORE),                           // runtime
    ERR_EVAL_CTX(Tag.DMN_CORE),                                 // runtime    
    FEEL_PROBLEM(Tag.DMN_CORE),                                 // runtime
    ERR_EVAL(Tag.DMN_CORE),                                     // runtime
    ERR_INVOKE(Tag.DMN_CORE),                                   // runtime
    
    
    BKM_MISSING_EXPR(Tag.DMN_VALIDATION),                      // DUPS of MISSING_EXPRESSION ?
    BKM_MISSING_VAR(Tag.DMN_VALIDATION),
    CONTEXT_DUP_ENTRY(Tag.DMN_VALIDATION),
    CONTEXT_ENTRY_NOTYPEREF (Tag.DMN_VALIDATION),
    DECISION_MISSING_EXPR (Tag.DMN_VALIDATION),                // DUPS of MISSING_EXPRESSION ?
    DECISION_MISSING_VAR(Tag.DMN_VALIDATION),
    DRGELEM_NOT_UNIQUE (Tag.DMN_VALIDATION),
    DTABLE_MULTIPLEOUT_NAME (Tag.DMN_VALIDATION),
    DTABLE_MULTIPLEOUT_TYPEREF (Tag.DMN_VALIDATION),
    DTABLE_PRIORITY_MISSING_OUTVALS (Tag.DMN_VALIDATION),
    DTABLE_SINGLEOUT_NONAME (Tag.DMN_VALIDATION),
    DTABLE_SINGLEOUT_NOTYPEREF (Tag.DMN_VALIDATION),
    ELEMREF_MISSING_TARGET (Tag.DMN_VALIDATION),
    ELEMREF_NOHASH (Tag.DMN_VALIDATION),
    FAILED_VALIDATOR (Tag.DMN_VALIDATION),
    FAILED_XML_VALIDATION (Tag.DMN_VALIDATION),
    FORMAL_PARAM_DUPLICATED (Tag.DMN_VALIDATION),
    INPUTDATA_MISSING_VAR (Tag.DMN_VALIDATION),
    INVOCATION_INCONSISTENT_PARAM_NAMES(Tag.DMN_VALIDATION),
    INVOCATION_MISSING_TARGET(Tag.DMN_VALIDATION),
    INVOCATION_WRONG_PARAM_COUNT(Tag.DMN_VALIDATION),
    ITEMCOMP_DUPLICATED (Tag.DMN_VALIDATION),
    ITEMDEF_NOT_UNIQUE (Tag.DMN_VALIDATION),
    NAME_INVALID(Tag.DMN_VALIDATION),                           // DUPS of INVALID_NAME ?
    RELATION_DUP_COLUMN (Tag.DMN_VALIDATION),
    RELATION_ROW_CELL_NOTLITERAL (Tag.DMN_VALIDATION),
    RELATION_ROW_CELLCOUNTMISMATCH (Tag.DMN_VALIDATION),
    REQAUTH_NOT_KNOWLEDGESOURCE (Tag.DMN_VALIDATION),
    TYPEREF_NO_FEEL_TYPE(Tag.DMN_VALIDATION),                   // DUPS of NO_TYPE_REF_FOUND ?
    TYPEREF_NOT_FEEL_NOT_DEF(Tag.DMN_VALIDATION)                // DUPS of NO_TYPE_DEF_FOUND ?
    ;
    
    private Tag[] tags;

    DMNMessageTypeId(Tag... tags) {
        this.tags = tags;
    }

    public enum Tag {
        DMN_CORE, DMN_VALIDATION;
    }

}
