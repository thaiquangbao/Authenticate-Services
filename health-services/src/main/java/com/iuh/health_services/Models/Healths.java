package com.iuh.health_services.Models;

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
@Document(collection = "healths")
public class Healths {
    @Id
    private ObjectId id;
    private String heartbeat;
    private String bloodPressure;
    private String userName;
}
