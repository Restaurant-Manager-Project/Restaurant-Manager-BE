package com.example.Restaurant_Manager_BE.repositories.Custom.impl;

import com.example.Restaurant_Manager_BE.repositories.Custom.CustomClientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CustomClientRepositoryImpl  {
//    @PersistenceContext
//    private EntityManager entityManager;
//    @Override
//    public List<ClientDTO> findAll_fromClient_andInvoice(){
//        String jpql = "SELECT new com.example.Restaurant_Manager_BE.dto.ClientDTO(" +
//                "c.id, c.firstName, c.lastName, c.phone,COALESCE( SUM(i.total),0L) )," +
//                "FROM ClientEntity c " +
//                "LEFT JOIN InvoicesEntity i ON i.client.id = c.id " +
//                "WHERE c.isDeleted = false " +
//                "GROUP BY c.id, c.firstName, c.lastName, c.phone";
//        TypedQuery<ClientDTO> query = entityManager.createQuery(jpql, ClientDTO.class);
//        return query.getResultList();
//    }
}
