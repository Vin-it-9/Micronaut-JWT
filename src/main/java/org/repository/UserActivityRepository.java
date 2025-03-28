package org.repository;

import io.micronaut.data.jpa.repository.JpaRepository;
import io.netty.handler.codec.http2.Http2Connection;
import org.entity.User;
import org.entity.UserActivity;

public interface UserActivityRepository extends JpaRepository<UserActivity, Integer> {


    Http2Connection findByUserOrderByTimestampDesc(User user, int limit);


}
