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
    private String health_condition;
    private String status;
    private List<UserDto> lsUsers;
}
