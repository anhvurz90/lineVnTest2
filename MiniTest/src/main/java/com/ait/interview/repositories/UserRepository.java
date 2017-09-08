package com.ait.interview.repositories;

import java.util.List;

import com.ait.interview.domain.User;
import com.ait.interview.domain.UserLocation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {
  List<User> getAllByLocationId(Long location_id);

  @Query(value = "select u.id as userId, u.username as userName, l.name as location, u.total_chat as countChat, u.latest_chat as latestChat from user u "
          + "inner join location l "
          + "where u.location_id=l.id AND u.active in (1, 2) AND (l.id = ?1 or l.parent_id = ?1) "
          + "AND l.parent_id <> 1 "
          + "AND (match(u.username, u.latest_chat) AGAINST(?2 IN NATURAL LANGUAGE MODE) "
          + "OR match(l.name) AGAINST(?2 IN NATURAL LANGUAGE MODE)) "
          + "\n#pageable\n",
          countQuery = "select count(*) from user u inner join location l "
              + "where u.location_id=l.id AND u.active in (1, 2) AND (l.id = ?1 or l.parent_Id = ?1) "
              + "AND l.parent_Id <> 1 "
              + "AND (match(u.username, u.latest_chat) AGAINST(?2 IN NATURAL LANGUAGE MODE) "
              + "OR match(l.name) AGAINST(?2 IN NATURAL LANGUAGE MODE))",
         nativeQuery = true)
  Page<UserLocation> customUsersInLocation(Long locationId, String searchPattern, Pageable pageable);
  User findById(Long id);
}
