package com.example.springbootvalidation.by_mvc;

import com.example.springbootvalidation.by_interface.User;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.ValidationUtils;

public class UserValidator implements SmartValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        validate(target, errors, null);
    }

    private void rejectIfEmptyOrWhitespace(Errors errors, String...fields){
        for(String field : fields){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, "FieldIsEmpty");
        }
    }

    @Override
    public void validate(Object target, Errors errors, Object... validationHints) {
        User user = (User) target;

        rejectIfEmptyOrWhitespace(errors, "username", "firstName", "lastName");
    }
}
