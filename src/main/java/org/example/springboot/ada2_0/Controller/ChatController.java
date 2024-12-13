package org.example.springboot.ada2_0.Controller;

import lombok.AllArgsConstructor;
import org.example.springboot.ada2_0.Dao.ChatRepository;
import org.example.springboot.ada2_0.Entity.ChatMessage;
import org.example.springboot.ada2_0.Services.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final ChatRepository chatRepository;

    @MessageMapping("/chat.sendMessage/{roomid}")
    @SendTo("/topic/room/{roomid}")
    public ChatMessage sendMessage(@DestinationVariable Integer roomid, @Payload ChatMessage chatMessage) {
        return chatService.chatSavingService(chatMessage, roomid);
    }
    @GetMapping("/app/getall")
    public ResponseEntity<List<ChatMessage>> getall(){
        List<ChatMessage> messages = chatRepository.findAll();
        System.out.println("Messages from database: " + messages);
        return ResponseEntity.ok(messages);
    }



}

