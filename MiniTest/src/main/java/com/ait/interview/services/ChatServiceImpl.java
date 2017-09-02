package com.ait.interview.services;

import com.ait.interview.domain.Chat;
import com.ait.interview.domain.ChatCountByUser;
import com.ait.interview.domain.User;
import com.ait.interview.repositories.ChatRepository;
import com.ait.interview.repositories.UserRepository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component("chatService")
public class ChatServiceImpl implements ChatService {

  private final ChatRepository chatRepository;
  private final UserRepository userRepository;

  public ChatServiceImpl(ChatRepository chatRepository, UserRepository userRepository) {
    this.chatRepository = chatRepository;
    this.userRepository = userRepository;
  }
  @Override
  public int count(Long id) {
    return this.chatRepository.count(this.userRepository.findById(id)).get(0).getCount();
  }

  @Override
  public Chat getLatestChat(Long id) {
    List<Chat> chats = this.chatRepository.findByOwnerOrderByIdDesc(this.userRepository.findById(id));
    if (chats.size() > 0) {
      return chats.get(0);
    } else {
      return null;
    }

  }
}
