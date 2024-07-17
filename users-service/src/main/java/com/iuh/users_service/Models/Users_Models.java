package com.iuh.users_service.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users_Models {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "password")
    private String passWord;
    @Column(name = "email")
    private String email;
    @Column(name = "sex")
    private boolean sex;
    private String role;
    private String address;
    private String phone;
    private String dateOfBirth;
    private String image;
    private int processSignup;
}