package com.ait.interview.repositories;

import com.ait.interview.domain.User;
import com.ait.interview.domain.UserLocation;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {
  List<User> getAllByLocationId(Long location_id);

  @Query(value = "select u.id as userId, u.username as userName, u.location as location from User u "
      + "inner join u.location l "
      + "where  u.active in (1, 2) and (l.id = ?1 or l.parentId = ?1) "
      + "and l.parentId <> 1"
      + "group by u.id "
      + "order by l.id DESC")
  List<UserLocation> customUsersInLocation(Long locationId);
  User findById(Long id);
}
