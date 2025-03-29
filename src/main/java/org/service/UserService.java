package org.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.dto.UserProfileDTO;
import org.entity.User;
import org.exceptions.UsernameNotFoundException;
import org.repository.UserRepository;

@Singleton
public class UserService {

    @Inject
    private UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void updateLastLogin(String username) {
        User user = findByUsername(username);
        user.updateLastLogin();
        userRepository.update(user);
    }

    public UserProfileDTO getUserProfile(String username) {
        updateLastLogin(username);
        User user = findByUsername(username);
        return new UserProfileDTO(
                user.getUsername(),
                user.getEmail(),
                user.getUserId(),
                user.getLastLogin(),
                user.getRoles(),
                user.getCreatedAt()
        );
    }


}