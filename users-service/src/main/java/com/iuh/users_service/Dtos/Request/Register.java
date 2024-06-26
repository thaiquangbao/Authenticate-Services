package com.iuh.users_service.Dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Register {
    private String fullName;
    private String age;
    private String passWord;
    private String email;
    private String userName;
    private boolean sex;
    private String role;
}
