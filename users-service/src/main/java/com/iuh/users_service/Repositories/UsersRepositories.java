package com.iuh.users_service.Repositories;

import com.iuh.users_service.Models.Users_Models;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepositories extends JpaRepository<Users_Models, Long> {
    Users_Models findByPhone(String userName);
    boolean existsByPhoneAndIdIsNot(String phone, Long id);
    boolean existsByEmailAndIdIsNot(String email, Long id);
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
}
