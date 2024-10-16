package com.example.Restaurant_Manager_BE.repository.custom.impl;

import com.example.Restaurant_Manager_BE.entity.TableEntity;
import com.example.Restaurant_Manager_BE.repository.custom.TablesRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class TablesRepositoryCustomImpl implements TablesRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public boolean updateById(TableEntity table) {

        StringBuilder a = new StringBuilder("UPDATE tables t SET t.table_name = :name, t.status_id = :status_id, t.is_deleted = :is_deleted, t.password = :password WHERE t.table_id = :id");

        int result = entityManager.createQuery(a.toString())
                    .setParameter("name", table.getName())
                    .setParameter("status_id", table.getStatusTable().getId())
                    .setParameter("is_deleted", table.getIs_deleted())
                    .setParameter("password", table.getPassword())
                    .setParameter("id", table.getId())
                    .executeUpdate();
        return result > 0;
    }
}
