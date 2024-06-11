package com.iuh.health_services.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Users {
    private String id;
    private String email;
    private String userName;
    private String fullName;
    private boolean sex;
    private String age;
}
