package com.iuh.users_healths.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Healths {
    private ObjectId id;
    private String heartbeat;
    private String bloodPressure;
}
