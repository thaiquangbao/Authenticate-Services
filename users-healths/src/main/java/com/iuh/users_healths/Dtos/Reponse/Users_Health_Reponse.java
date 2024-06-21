package com.iuh.users_healths.Dtos.Reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users_Health_Reponse {
    private String email;
    private String userName;
    private String fullName;
    private boolean sex;
    private String age;
    private List<Health_Status> health_status;
}
