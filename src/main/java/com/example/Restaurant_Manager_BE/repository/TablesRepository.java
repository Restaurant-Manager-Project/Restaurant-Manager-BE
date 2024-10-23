package com.example.Restaurant_Manager_BE.repository;
import com.example.Restaurant_Manager_BE.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

    public interface TablesRepository extends JpaRepository<TableEntity, Long> {
    TableEntity findByDirection(String direction);

}
