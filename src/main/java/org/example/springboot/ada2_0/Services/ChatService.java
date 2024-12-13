package org.example.springboot.ada2_0.Services;

import lombok.AllArgsConstructor;
import org.example.springboot.ada2_0.Dao.ChatRepository;
import org.example.springboot.ada2_0.Dao.UserRepository;
import org.example.springboot.ada2_0.Entity.ChatMessage;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChatService {
    private UserRepository userRepository;
    private ChatRepository chatRepository;

    public ChatMessage chatSavingService(ChatMessage chatMessage, Integer id) {
        chatMessage.setSender("Anonymous");
        chatMessage.setRoomid(id);
        return chatRepository.save(chatMessage);
    }

}
