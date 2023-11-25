package com.hadhemi.Task_Management.controllers;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "chat")
public class ChatMessageEntity {

    @Id
    private String id;
    private String sender;
    private String recipient;
    private String content;
}
