package org.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.netty.handler.codec.http2.Http2Connection;
import org.entity.User;
import org.entity.UserActivity;
import org.hibernate.annotations.processing.Find;

import java.util.List;


@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Integer> {


    List<UserActivity> findByUserOrderByTimestampDesc(User user);

    /**
     * Find activities with limit
     */
    List<UserActivity> findByUser(User user, io.micronaut.data.model.Pageable pageable);


}
