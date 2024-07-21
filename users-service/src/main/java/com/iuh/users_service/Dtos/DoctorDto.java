package com.iuh.users_service.Dtos;

import com.iuh.users_service.Dtos.Reponse.Rating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DoctorDto {
    private Long id;
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
