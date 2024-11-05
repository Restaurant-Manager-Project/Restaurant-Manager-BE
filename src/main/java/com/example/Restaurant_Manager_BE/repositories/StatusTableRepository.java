package com.example.Restaurant_Manager_BE.repositories;

import com.example.Restaurant_Manager_BE.entities.StatusTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import optional
import java.util.List;
import java.util.Optional;

@Repository
public interface StatusTableRepository extends JpaRepository<StatusTableEntity, Long> {
    @Query("SELECT s FROM StatusTableEntity s WHERE s.name = :name")
    Optional<StatusTableEntity> findByName(@Param("name") String name);
    Optional<StatusTableEntity> findByNameIgnoreCase(String name);
}
