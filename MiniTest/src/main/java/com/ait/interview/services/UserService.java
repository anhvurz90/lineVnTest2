package com.ait.interview.services;

import com.ait.interview.domain.User;
import com.ait.interview.domain.UserLocation;
import java.util.List;

public interface UserService {
  List<User> getUsersByLocation(Long locationId);
  List<UserLocation> getUsersInLocation(Long locationId);
  User getById(Long id);
}
