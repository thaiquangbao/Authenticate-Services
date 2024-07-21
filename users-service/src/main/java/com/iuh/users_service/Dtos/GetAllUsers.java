package com.iuh.users_service.Dtos;

import com.iuh.users_service.Dtos.Reponse.ProfileUsers;
import com.iuh.users_service.Dtos.Reponse.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUsers {
    private List<ProfileUsers> data;
    private Token token;

}
