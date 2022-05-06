package com.example.demo.data.jpa.repository;

import com.example.demo.data.jpa.entity.PassportEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassportRepository extends JpaRepository<PassportEntity, Long> {
    boolean existsByUID(String UID);
    boolean existsByUserId(Long id);

    @EntityGraph(attributePaths = {"user"})
    List<PassportEntity> findAll();

    @EntityGraph(attributePaths = {"user"})
    Page<PassportEntity> findAllBy(Pageable pageable);
}
