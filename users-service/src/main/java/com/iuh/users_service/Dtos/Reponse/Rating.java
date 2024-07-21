package com.iuh.users_service.Dtos.Reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Rating {
    private int rate;
    private UserRating user_rating;
}
