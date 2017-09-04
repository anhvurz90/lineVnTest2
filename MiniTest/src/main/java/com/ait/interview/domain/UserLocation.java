package com.ait.interview.domain;

public interface UserLocation {
  Long getUserId();
  String getUserName();
  String getLocation();
  Integer getCountChat();
  void setCountChat(Integer i);
  String getLatestChat();
  void setLatestChat(String chat);
}
