package com.iuh.users_service.Dtos.Reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DoctorFeign {
    private Long id_user;
    private String fullName;
    private String passWord;
    private String email;
    private boolean sex;
    private String role;
    private String address;
    private String phone;
    private String dateOfBirth;
    private String image;
    private int processSignup;
    private String specialize;
    private List<Rating> rating;
}
