package com.example.Restaurant_Manager_BE.repositories;
import com.example.Restaurant_Manager_BE.entities.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<TableEntity, Long> {
    TableEntity findByDirection(String direction);

}
