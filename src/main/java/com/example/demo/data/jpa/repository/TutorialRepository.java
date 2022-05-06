package com.example.demo.data.jpa.repository;

import com.example.demo.data.jpa.entity.TutorialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialRepository extends JpaRepository<TutorialEntity, Long> {
}