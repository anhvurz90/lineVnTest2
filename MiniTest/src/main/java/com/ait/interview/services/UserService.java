package com.ait.interview.services;

import com.ait.interview.domain.User;
import com.ait.interview.domain.UserLocation;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
  List<User> getUsersByLocation(Long locationId);
  Page<UserLocation> getUsersInLocation(Long locationId, String searchPattern, Pageable pageable);
  User getById(Long id);
}
