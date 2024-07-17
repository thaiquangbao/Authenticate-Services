package com.iuh.users_service.Dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUsers {
    private Long id;
    private String fullName;
    private String email;
    private boolean sex;
    private String passWord;
    private String address;
    private String phone;
    private String dateOfBirth;
    private String image;
    private int processSignup;
}
