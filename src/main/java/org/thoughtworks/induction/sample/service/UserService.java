package org.thoughtworks.induction.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thoughtworks.induction.sample.repository.UserRepository;
import org.thoughtworks.induction.sample.bean.CreateUserCommand;
import org.thoughtworks.induction.sample.exception.InvalidEmailException;
import org.thoughtworks.induction.sample.bean.User;

import javax.validation.Validator;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    public UserService() {
    }

    public User create(CreateUserCommand userCommand) throws InvalidEmailException {
        Optional<User> user = userRepository.findByEmail(userCommand.getEmail());
        if (user.isPresent()) {
            throw new InvalidEmailException();
        }
        User newUser = User.create(userCommand);
        validator.validate(newUser);
        return userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList()
        );
    }
}
