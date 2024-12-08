package org.example.springboot.ada2_0.Dao;

import org.example.springboot.ada2_0.Entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ChatRepository extends JpaRepository<ChatMessage,Long> {
//    public List<ChatMessage> getAllByRoomid(Integer roomid);
}
