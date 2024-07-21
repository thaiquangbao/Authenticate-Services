package com.iuh.users_service.Dtos;

import com.iuh.users_service.Dtos.Reponse.DoctorAuth;
import com.iuh.users_service.Dtos.Reponse.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DoctorDtoCheck {
    private DoctorAuth data;
    private Token token;
}
