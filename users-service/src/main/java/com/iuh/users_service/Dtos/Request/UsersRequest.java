package com.iuh.users_service.Dtos.Request;

import com.iuh.users_service.Dtos.Reponse.Rating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersRequest {
    private Long id;
    private String fullName;
    private String email;
    private boolean sex;
    private String passWord;
    private String address;
    private String phone;
    private String dateOfBirth;
    private String image;
    private String role;
    private int processSignup;
    private String specialize;
    private List<Rating> rating;
}
