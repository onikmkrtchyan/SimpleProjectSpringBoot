package com.example.demo.data.jpa.repository;

import com.example.demo.data.jpa.entity.CarEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    @EntityGraph(attributePaths = {"user"})
    List<CarEntity> findByUserId(Long id);

    boolean existsByCarNumber(String carNumber);

    @EntityGraph(attributePaths = {"user"})
    Page<CarEntity> findAllBy(Pageable pageable);
}
