package com.iuh.users_healths.Dtos.Reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Users_Health_Feign {
    private String email;
    private String userName;
    private String fullName;
    private boolean sex;
    private String age;
    private List<Health_Status_Feign> lsHealth;
}
