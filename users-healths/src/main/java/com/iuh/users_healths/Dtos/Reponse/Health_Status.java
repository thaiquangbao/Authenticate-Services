package com.iuh.users_healths.Dtos.Reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Health_Status {
    private String health_condition;
    private String status;
    private String created_at;
}
