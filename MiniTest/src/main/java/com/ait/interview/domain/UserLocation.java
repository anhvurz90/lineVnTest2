package com.ait.interview.domain;

import com.ait.interview.services.UserService;

public interface UserLocation {
  Long getUserId();
  String getUserName();
  Location getLocation();
  Integer getCountChat();
  void setCountChat(Integer i);
  Chat getLatestChat();
  void setLatestChat(Chat chat);
}
