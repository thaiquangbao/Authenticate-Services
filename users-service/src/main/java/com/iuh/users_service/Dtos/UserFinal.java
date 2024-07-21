package com.iuh.users_service.Dtos;

import com.iuh.users_service.Dtos.Reponse.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserFinal {
    private UserDto data;
    private Token token;
}
