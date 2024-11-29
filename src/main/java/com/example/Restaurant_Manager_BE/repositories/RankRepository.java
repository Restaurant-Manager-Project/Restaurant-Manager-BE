package com.example.Restaurant_Manager_BE.repositories;
import com.example.Restaurant_Manager_BE.entities.RankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface RankRepository extends JpaRepository<RankEntity, Long> {
    List<RankEntity> findAll();
}
