package com.example.Restaurant_Manager_BE.repositories;

import com.example.Restaurant_Manager_BE.entities.ClientEntity;
import com.example.Restaurant_Manager_BE.entities.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity,Long> {
    Optional<InvoiceEntity> findByTimeCreate(Date timeCreate);
    List<InvoiceEntity> findByClient(ClientEntity client);
}
