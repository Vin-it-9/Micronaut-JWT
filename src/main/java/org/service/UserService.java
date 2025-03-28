package org.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.dto.UserProfileDTO;
import org.entity.User;
import org.exceptions.UserAlreadyExistsException;
import org.exceptions.UsernameNotFoundException;
import org.repository.UserRepository;
import org.security.PasswordEncoder;

@Singleton
public class UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private PasswordEncoder passwordEncoder;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User register(String username, String email, String password) {

        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException("Username already taken");
        }

        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("Email already in use");
        }

        User user = new User(
                username,
                email,
                passwordEncoder.encode(password)
        );

        user.getRoles().add("ROLE_USER");

        return userRepository.save(user);
    }

    public void updateLastLogin(String username) {
        User user = findByUsername(username);
        user.updateLastLogin();
        userRepository.update(user);
    }

    public UserProfileDTO getUserProfile(String username) {
        User user = findByUsername(username);
        return new UserProfileDTO(
                user.getUsername(),
                user.getEmail(),
                user.getUserId(),
                user.getLastLogin(),
                user.getRoles(),
                user.getStatus().name()
        );
    }
}