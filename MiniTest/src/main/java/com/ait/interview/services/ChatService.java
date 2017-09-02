package com.ait.interview.services;

import com.ait.interview.domain.Chat;
import com.ait.interview.domain.User;

public interface ChatService {
  int count(Long id);
  Chat getLatestChat(Long id);
}
