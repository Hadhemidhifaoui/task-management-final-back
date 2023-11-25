package com.hadhemi.Task_Management.repository;

import com.hadhemi.Task_Management.controllers.ChatMessageEntity;
import com.hadhemi.Task_Management.models.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    @Query("SELECT c FROM ChatMessage c WHERE c.sender = :idSender")
    public List<ChatMessage> findAllMessageBetweenRecAndSender(String idSender , String idRec);
}

