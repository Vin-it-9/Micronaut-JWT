package org.service;

import io.micronaut.data.model.Pageable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.dto.UserActivityDTO;
import org.entity.User;
import org.entity.UserActivity;
import org.repository.UserActivityRepository;
import org.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class UserActivityService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserActivityRepository activityRepository;

    public void logActivity(String username, String activityType, String description) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserActivity activity = new UserActivity();
        activity.setUser(user);
        activity.setActivityType(activityType);
        activity.setDescription(description);
        activity.setTimestamp(LocalDateTime.now());

        activityRepository.save(activity);
    }

    public List<UserActivityDTO> getRecentActivity(String username, int limit) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

      List<UserActivity> activities = activityRepository.findByUserOrderByTimestampDesc(user)
            .stream()
            .limit(limit)
            .collect(Collectors.toList());

         return activities.stream()
            .map(activity -> new UserActivityDTO(
                    activity.getActivityType(),
                    activity.getDescription(),
                    activity.getTimestamp()
            ))
            .collect(Collectors.toList());

    }
}