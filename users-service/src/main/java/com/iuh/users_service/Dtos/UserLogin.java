package com.iuh.users_service.Dtos;

import com.iuh.users_service.Dtos.Reponse.Token;
import com.iuh.users_service.Dtos.Request.UserDtoUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLogin {
    private UserDtoUpdate data;
    private Token token;
}
