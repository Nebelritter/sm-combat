/**
 * 
 */
package de.chaosbutterfly.smcombat.client.vaadin.validators;

import com.vaadin.data.validator.AbstractValidator;

/**
 * @author Alti
 *
 */
// Validator for validating the passwords
public final class PasswordValidator extends AbstractValidator<String> {

    /**  */
    private static final long serialVersionUID = 1L;

    public PasswordValidator() {
        super("The password provided is not valid");
    }

    @Override
    protected boolean isValidValue(String value) {
        // Password must be at least 8 characters long and contain at least
        // one number
        if (value != null && (value.length() < 8 || !value.matches(".*\\d.*"))) {
            return false;
        }
        return true;
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}

