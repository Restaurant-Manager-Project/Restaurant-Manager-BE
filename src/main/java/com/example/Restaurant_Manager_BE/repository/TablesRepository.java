package com.example.Restaurant_Manager_BE.repository;
import com.example.Restaurant_Manager_BE.entity.TableEntity;
import com.example.Restaurant_Manager_BE.repository.custom.ProductsRepositoryCustom;
import com.example.Restaurant_Manager_BE.repository.custom.TablesRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TablesRepository extends JpaRepository<TableEntity, Long>, TablesRepositoryCustom {


    TableEntity findByPassword(String password);
}
