package com.ait.interview.services;

import com.ait.interview.domain.User;
import com.ait.interview.domain.UserLocation;
import com.ait.interview.repositories.UserRepository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Component;

@Component("userService")
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  @Override
  public List<User> getUsersByLocation(Long locationId) {
    return this.userRepository.getAllByLocationId(locationId);
  }

  @Override
  public List<UserLocation> getUsersInLocation(Long locationId, Pageable pageable) {
    return this.userRepository.customUsersInLocation(locationId, pageable);
  }

  @Override
  public User getById(Long id) {
    return this.userRepository.findById(id);
  }
  @Override
  public int countUsersInLocation(long locationId) {
    return userRepository.countUsersInLocation(locationId);
  }
}
