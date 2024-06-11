package com.iuh.users_service.Dtos.Reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUsers {
    private Long id;
    private String fullName;
    private String age;
    private String passWord;
    private String email;
    private String userName;
    private boolean sex;
    private String role;
}
