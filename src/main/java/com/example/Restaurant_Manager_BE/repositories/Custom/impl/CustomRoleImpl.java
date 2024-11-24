package com.example.Restaurant_Manager_BE.repositories.Custom.impl;

import com.example.Restaurant_Manager_BE.entities.RoleEntity;
import com.example.Restaurant_Manager_BE.repositories.Custom.CustomRole;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class CustomRoleImpl implements CustomRole {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Optional<RoleEntity> getRoleWithPermission(Long idRole) {
        TypedQuery<RoleEntity> query = entityManager.createQuery("SELECT r FROM RoleEntity r JOIN FETCH r.permissions WHERE r.id = :idRole", RoleEntity.class);
        query.setParameter("idRole", idRole);
        return Optional.ofNullable(query.getSingleResult());
    }
}
