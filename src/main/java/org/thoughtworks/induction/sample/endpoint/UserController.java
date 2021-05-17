package org.thoughtworks.induction.sample.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thoughtworks.induction.sample.bean.CreateUserCommand;
import org.thoughtworks.induction.sample.bean.User;
import org.thoughtworks.induction.sample.bean.UserView;
import org.thoughtworks.induction.sample.service.UserService;
import org.thoughtworks.induction.sample.exception.InvalidEmailException;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ResponseEntity<UserView> create(@RequestBody CreateUserCommand userCommand) throws InvalidEmailException {
        User user = userService.create(userCommand);
        return new ResponseEntity<>(new UserView(user), HttpStatus.CREATED);
    }
}
