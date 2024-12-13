package org.example.springboot.ada2_0.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mesages")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
//   @JsonProperty("content")
    private String message;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private Integer roomid;

    public ChatMessage(String message, String sender, Integer roomid) {
        this.message = message;
        this.sender = sender;
        this.roomid = roomid;
    }



}
