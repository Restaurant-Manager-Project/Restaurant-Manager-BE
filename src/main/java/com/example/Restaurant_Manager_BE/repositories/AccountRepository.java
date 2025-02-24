package com.example.Restaurant_Manager_BE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Restaurant_Manager_BE.entities.AccountEntity;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {
    Optional<AccountEntity> findByUsername(String username);
    Optional<AccountEntity> findByRefreshToken(String refreshToken);
}
