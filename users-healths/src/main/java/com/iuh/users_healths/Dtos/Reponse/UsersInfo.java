package com.iuh.users_healths.Dtos.Reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersInfo {
    private String id;
    private String email;
    private String userName;
    private String fullName;
    private boolean sex;
    private String age;
}
