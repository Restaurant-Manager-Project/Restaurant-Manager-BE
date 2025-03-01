package com.example.Restaurant_Manager_BE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Restaurant_Manager_BE.entities.AccountEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {
    Optional<AccountEntity> findByUsername(String username);
    Optional<AccountEntity> findByRefreshToken(String refreshToken);

    @Modifying
    @Transactional
    @Query(value = "UPDATE accounts SET refresh_token = null WHERE username = :username", nativeQuery = true)
    int deleteRefreshTokenByUsername(@Param("username") String username);

}
