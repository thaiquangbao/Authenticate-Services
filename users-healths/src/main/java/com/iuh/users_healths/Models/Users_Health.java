package com.iuh.users_healths.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "users_health")
public class Users_Health {
    @Id
    private ObjectId id;
    private String health_condition;
    private String message_suggest;
    private String status;
    private String created_at;
    private Users users;
    private Healths healths;
}
