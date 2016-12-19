package org.kie.dmn.feel.util;

/**
 * Utility class for I18N messages.
 *
 */
public class Msg {
    private static final String NULL_OR_UNKNOWN_OPERATOR = "Null or unknown operator";
    private static final String NEGATING_A_NULL = "Negating a null";
    private static final String ERROR_ACCESSING_QUALIFIED_NAME = "Error accessing qualified name: %s";
    private static final String ERROR_EVALUATING_PATH_EXPRESSION = "Error evaluating path expression: %s. %s";
    private static final String VALUE_NULL_EXPR_NOT_NULL_AND_NOT_UNARY_TEST_EVALUATING_THIS_NODE_AS_FALSE = "value == null, expr != null and not Unary test, Evaluating this node as FALSE.";
    private static final String EXPRESSION_IS_RANGE_BUT_VALUE_IS_NOT_COMPARABLE = "Expression is Range but value is not Comparable";
    private static final String CONDITION_WAS_NOT_A_BOOLEAN = "Condition was not a Boolean";
    private static final String FUNCTION_NOT_FOUND = "Function not found: '%s'";
    private static final String ERROR_EXECUTING_LIST_FILTER = "Error executing list filter: %s";
    private static final String INDEX_OUT_OF_BOUND = "Index out of bound";
    private static final String X_TYPE_INCOMPATIBLE_WITH_Y_TYPE = "%s type incompatible with %s type";
    private static final String EVALUATED_TO_NULL = "%s evaluated to null";
    private static final String IS_NULL = "%s is null";
    private static final String BASE_NODE_EVALUATE_CALLED = "BaseNode evaluate called";
    private static final String ERROR_RESOLVING_EXTERNAL_FUNCTION_AS_DEFINED_BY = "Error resolving external function as defined by: %s";
    private static final String UNABLE_TO_FIND_EXTERNAL_FUNCTION_AS_DEFINED_BY = "Unable to find external function as defined by: %s";
    private static final String PARAMETER_COUNT_MISMATCH_ON_FUNCTION_DEFINITION = "Parameter count mismatch on function definition: %s";
    private static final String CAN_T_INVOKE_AN_UNARY_TEST_WITH_S_PARAMETERS_UNARY_TESTS_REQUIRE_1_SINGLE_PARAMETER = "Can't invoke an unary test with %s parameters. Unary tests require 1 single parameter.";
    
    public static String parameterCountMismatch(String name) {
        return String.format(PARAMETER_COUNT_MISMATCH_ON_FUNCTION_DEFINITION, name);
    }

    public static String unableToFindExternalFunction(String name) {
        return String.format(UNABLE_TO_FIND_EXTERNAL_FUNCTION_AS_DEFINED_BY, name);
    }

    public static String errorResolvingExternalFunction(String name) {
        return String.format(ERROR_RESOLVING_EXTERNAL_FUNCTION_AS_DEFINED_BY, name);
    }

    public static String baseNodeEvaluateCalled() {
        return BASE_NODE_EVALUATE_CALLED;
    }

    public static String isNull(String x) {
        return String.format(IS_NULL, x);
    }

    public static String evaluatedToNull(String x) {
        return String.format(EVALUATED_TO_NULL, x);
    }

    public static String typeIncompatible(String x, String y) {
        return String.format(X_TYPE_INCOMPATIBLE_WITH_Y_TYPE, x, y);
    }

    public static String indexOutOfBound() {
        return INDEX_OUT_OF_BOUND;
    }

    public static String errorExecutingListFilter(String filter) {
        return String.format(ERROR_EXECUTING_LIST_FILTER, filter);
    }

    public static String functionNotFound(String functionName) {
        return String.format(FUNCTION_NOT_FOUND, functionName);
    }

    public static String unaryTestMultipleParams(int size) {
        return String.format(CAN_T_INVOKE_AN_UNARY_TEST_WITH_S_PARAMETERS_UNARY_TESTS_REQUIRE_1_SINGLE_PARAMETER, size);
    }

    public static String conditionWasNotABoolean() {
        return CONDITION_WAS_NOT_A_BOOLEAN;
    }

    public static String expressionIsRangeButValueNotComparable() {
        return EXPRESSION_IS_RANGE_BUT_VALUE_IS_NOT_COMPARABLE;
    }

    public static String valueNullExprNotNullAndNotUnary() {
        return VALUE_NULL_EXPR_NOT_NULL_AND_NOT_UNARY_TEST_EVALUATING_THIS_NODE_AS_FALSE;
    }

    public static String errorEvaluatingPathExpression(String expression, String name) {
        return String.format(ERROR_EVALUATING_PATH_EXPRESSION, expression, name);
    }

    public static String errorAccessingQualifiedName(String name) {
        return String.format(ERROR_ACCESSING_QUALIFIED_NAME, name);
    }

    public static String negatingANull() {
        return NEGATING_A_NULL;
    }

    public static String NullOrUnknownOperator() {
        return NULL_OR_UNKNOWN_OPERATOR;
    }
    
    
}
