package com.example.Restaurant_Manager_BE.repositories;

import com.example.Restaurant_Manager_BE.entities.RoleEntity;
import com.example.Restaurant_Manager_BE.repositories.Custom.CustomRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>, CustomRole {

}
