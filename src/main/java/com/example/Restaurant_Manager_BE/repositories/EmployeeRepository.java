package com.example.Restaurant_Manager_BE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.Restaurant_Manager_BE.entities.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
    EmployeeEntity findByAccount_Username(String username);
}
