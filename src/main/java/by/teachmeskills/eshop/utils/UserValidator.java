package by.teachmeskills.eshop.utils;

import by.teachmeskills.eshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserValidator implements ConstraintValidator<UserConstraint, String> {
    @Autowired
    private UserRepository userRepository;

    public UserValidator() {
    }

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.getUserByName(contactField).isEmpty();
    }
}