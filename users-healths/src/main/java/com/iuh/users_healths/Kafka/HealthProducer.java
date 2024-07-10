package com.iuh.users_healths.Kafka;

import org.bson.types.ObjectId;

public record HealthProducer(
        ObjectId id,
        String heartbeat,
        String bloodPressure,
        String userName,
        String id_redis
) {
}
