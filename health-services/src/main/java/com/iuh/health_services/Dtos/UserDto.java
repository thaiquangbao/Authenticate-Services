package com.iuh.health_services.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String email;
    private String userName;
    private String fullName;
    private boolean sex;
    private String age;
}
