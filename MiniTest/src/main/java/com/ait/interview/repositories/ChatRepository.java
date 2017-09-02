package com.ait.interview.repositories;

import com.ait.interview.domain.Chat;
import com.ait.interview.domain.ChatCountByUser;
import com.ait.interview.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface ChatRepository extends Repository<Chat, Long> {
  @Query("select count(c) as count from Chat c where c.owner = ?1 and c.status in (1,2)")
  List<ChatCountByUser> count(User owner);
  List<Chat> findByOwnerOrderByIdDesc(User owner);
}
