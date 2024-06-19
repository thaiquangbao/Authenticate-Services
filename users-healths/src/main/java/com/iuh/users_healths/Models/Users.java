package com.iuh.users_healths.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Document(collection = "users")
public class Users {
    private String id;
    private String email;
    private String userName;
    private String fullName;
    private boolean sex;
    private String age;

}
