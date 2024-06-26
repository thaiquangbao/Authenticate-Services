package com.iuh.health_services.Dtos.Respone;

import com.iuh.health_services.Dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StatusOfAllUsers {
    private String id;
    private String email;
    private String userName;
    private String fullName;
    private boolean sex;
    private String age;
    private List<Health_Status> lsStatus;
}
