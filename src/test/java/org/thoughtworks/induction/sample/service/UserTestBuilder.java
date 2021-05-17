package org.thoughtworks.induction.sample.service;

import org.thoughtworks.induction.sample.bean.CreateUserCommand;
import org.thoughtworks.induction.sample.bean.User;

public class UserTestBuilder {
    private final User.UserBuilder userBuilder;

    public UserTestBuilder() {
        userBuilder = User.builder()
                .id(1L)
                .email("testemail@test.com");
    }

    public static CreateUserCommand buildCreateUserCommand() {
        return new CreateUserCommand("testemail@test.com", "foobar");
    }

    public User build() {
        return userBuilder.build();
    }

    public UserTestBuilder withEmail(String email) {
        userBuilder.email(email);
        return this;
    }
}
