package com.iuh.users_service.Dtos;

import com.iuh.users_service.Dtos.Reponse.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DoctorLogin {
    private DoctorDto data;
    private Token token;
}
