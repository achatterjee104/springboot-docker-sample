package org.thoughtworks.induction.sample.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class CreateUserCommand {
    private final String email;
    private final String password;
}
