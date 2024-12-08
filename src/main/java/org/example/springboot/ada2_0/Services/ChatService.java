package org.example.springboot.ada2_0.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.springboot.ada2_0.Dao.ChatRepository;
import org.example.springboot.ada2_0.Dao.UserRepository;
import org.example.springboot.ada2_0.Dto.ChatMessageDto;
import org.example.springboot.ada2_0.Entity.ChatMessage;
import org.example.springboot.ada2_0.Entity.MyUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
@AllArgsConstructor
public class ChatService {
    private UserRepository userRepository;
    private ChatRepository chatRepository;
    @Transactional //This ensures database operations are atomic.
    public ChatMessage saveMessage(ChatMessageDto chatMessageDto) {
        ChatMessage message = new ChatMessage();
        Optional<MyUser> user = userRepository.findByGroup(chatMessageDto.getGroup_id());

        if (user.isPresent()) {
            message.setMessage(chatMessageDto.getMessage());
            message.setSender(user.get().getUsername());
            message.setRoomid(chatMessageDto.getGroup_id());
            try {
                chatRepository.save(message);
                return message;
            } catch (Exception e) {

                //Handle the exception appropriately. For example, re-throw a custom exception.
                throw new RuntimeException("Failed to save message to database", e);
            }

        } else {

            return null; // Or throw a custom exception
        }
    }

}
