package com.ait.interview.repositories;

import com.ait.interview.domain.User;
import com.ait.interview.domain.UserLocation;

import java.util.List;

import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {
  List<User> getAllByLocationId(Long location_id);

  @Query(value = "select u.id as userId, u.username as userName, u.location.name as location, u.totalChat as countChat, u.latestChat as latestChat from User u "
      + "where u.active in (1, 2) and (u.location.id = ?1 or u.location.parentId = ?1) "
      + "and u.location.parentId <> 1")
  List<UserLocation> customUsersInLocation(Long locationId, Pageable pageable);
  User findById(Long id);

  @Query(value = "select count(*) from  User u "
          + "where  u.active in (1, 2) and (u.location.id = ?1 or u.location.parentId = ?1) "
          + "and u.location.parentId <> 1")
  int countUsersInLocation(long locationId);
}
