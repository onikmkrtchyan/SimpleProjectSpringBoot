package com.example.demo.data.jpa.repository;

import com.example.demo.data.jpa.entity.PDFEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PDFRepository extends JpaRepository<PDFEntity,Long> {

}
