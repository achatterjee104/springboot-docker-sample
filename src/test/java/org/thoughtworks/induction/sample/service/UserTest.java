package org.thoughtworks.induction.sample.service;

import org.junit.jupiter.api.Test;
import org.thoughtworks.induction.sample.bean.CreateUserCommand;
import org.thoughtworks.induction.sample.bean.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.thoughtworks.induction.sample.bean.User.PASSWORD_ENCODER;

class UserTest {
    @Test
    void shouldBeEmailMandatory() {
        CreateUserCommand userCommand = new CreateUserCommandTestBuilder().withEmptyEmail().build();
        User user = User.create(userCommand);

        Set<ConstraintViolation<User>> constraintViolations = constraintsValidator().validate(user);

        assertFalse(constraintViolations.isEmpty());
        ConstraintViolation<User> next = constraintViolations.iterator().next();
        assertEquals("Email is mandatory", next.getMessage());
    }

    @Test
    void shouldBePasswordMandatory() {
        CreateUserCommand userCommand = new CreateUserCommandTestBuilder().withEmptyPassword().build();
        User user = User.create(userCommand);

        Set<ConstraintViolation<User>> constraintViolations = constraintsValidator().validate(user);

        assertFalse(constraintViolations.isEmpty());
        assertEquals("Password is mandatory", constraintViolations.iterator().next().getMessage());
    }

    @Test
    void shouldCreateUserEncryptPassword() {
        CreateUserCommand userCommand = new CreateUserCommandTestBuilder().build();
        User user = User.create(userCommand);

        assertTrue(PASSWORD_ENCODER.matches("foobar", user.getPassword()));
    }

    private Validator constraintsValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        return validatorFactory.getValidator();
    }
}