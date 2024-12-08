package org.example.springboot.ada2_0.Controller;

import org.example.springboot.ada2_0.Dao.ChatRepository;
import org.example.springboot.ada2_0.Dto.ChatMessageDto;
import org.example.springboot.ada2_0.Dto.ResourceDto;
import org.example.springboot.ada2_0.Entity.ChatMessage;
import org.example.springboot.ada2_0.Services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private ChatRepository chatRepository;

    @MessageMapping("/chat.sendMessage/{roomid}")
    @SendTo("/topic/room/{roomid}")
    public ChatMessage sendMessage(@DestinationVariable Integer roomid, @Payload ChatMessage chatMessage) {
        String sender = "Anonymous"; // Replace with logic to get actual sender if needed
        chatMessage.setSender(sender);
        chatMessage.setRoomid(roomid); //Ensure roomid is set


        try {
            ChatMessage savedMessage = chatRepository.save(chatMessage);
            return savedMessage;
        } catch (Exception e) {
            return null; // or handle the error appropriately
        }
    }
    @GetMapping("/app/getall")
    public ResponseEntity<List<ChatMessage>> getall(){
        List<ChatMessage> messages = chatRepository.findAll();
        System.out.println("Messages from database: " + messages);
        return ResponseEntity.ok(messages);
    }



}

