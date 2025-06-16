package org.example.techmateaccessories.validation;

import org.example.techmateaccessories.domain.dto.RegisterDTO;
import org.springframework.beans.BeanWrapperImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Service;

@Service
public class PasswordMatchingValidator implements ConstraintValidator<PasswordMatching, RegisterDTO> {


    private String password;
    private String confirmPassword;

    @Override
    public void initialize(PasswordMatching matching) {
        this.password = matching.password();
        this.confirmPassword = matching.confirmPassword();
    }

    @Override
    public boolean isValid(RegisterDTO value, ConstraintValidatorContext context) {

        Object passwordValue = new BeanWrapperImpl(value).getPropertyValue(password);
        Object confirmPasswordValue = new BeanWrapperImpl(value).getPropertyValue(confirmPassword);

        boolean valid = (passwordValue != null) ? passwordValue.equals(confirmPasswordValue) : confirmPasswordValue == null;

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Passwords must match!")
                    .addPropertyNode(confirmPassword)
                    .addConstraintViolation();
        }

        return valid ;
    }
}