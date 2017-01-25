package org.kie.dmn.validation;


public class DMNValidatorFactory {

    public static DMNValidator newValidator() {
        return new DMNValidatorImpl();
    }
}
