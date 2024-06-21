package com.iuh.health_services.Dtos.Respone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Health_Status {
    private String health_condition;
    private String status;
    private String created_at;
}
