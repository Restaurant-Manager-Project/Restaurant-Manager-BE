package com.example.Restaurant_Manager_BE.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Restaurant_Manager_BE.entities.CategoryEntity;
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findById(int id);
    Optional<CategoryEntity> findByName(String name);
    List<CategoryEntity> findByIsDeletedFalse();
}
