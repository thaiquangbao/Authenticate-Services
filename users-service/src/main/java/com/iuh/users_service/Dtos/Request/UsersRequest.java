package com.iuh.users_service.Dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersRequest {
    private String id;
    private String email;
    private String userName;
    private String fullName;
    private boolean sex;
    private String age;
    private String address;
    private String phone;
    private String dateOfBirth;
    private String image;
}
