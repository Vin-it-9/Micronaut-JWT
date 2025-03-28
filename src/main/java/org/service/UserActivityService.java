//package org.service;
//
//import io.micronaut.serde.annotation.Serdeable;
//import io.micronaut.transaction.annotation.Transactional;
//import jakarta.inject.Inject;
//import org.entity.User;
//import org.entity.UserActivity;
//import org.exceptions.UsernameNotFoundException;
//import org.repository.UserActivityRepository;
//import org.repository.UserRepository;
//
//@Serdeable
//@Transactional
//public class UserActivityService {
//
//    @Inject
//    private UserActivityRepository activityRepository;
//
//    @Inject
//    private UserRepository userRepository;
//
//    public void logActivity(String username, String activityType, String description) {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        UserActivity activity = new UserActivity(user, activityType, description);
//        activityRepository.save(activity);
//    }
//
////    public List<UserActivityDTO> getRecentActivity(String username, int limit) {
////        User user = userRepository.findByUsername(username)
////                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
////
////        return activityRepository.findByUserOrderByTimestampDesc(user, limit)
////                .stream()
////                .map(activity -> new UserActivityDTO(
////                        activity.getActivityType(),
////                        activity.getDescription(),
////                        activity.getTimestamp()
////                ))
////                .collect(Collectors.toList());
////    }
//
//}