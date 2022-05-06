package com.example.demo.service.excel;

import com.example.demo.data.jpa.entity.TutorialEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExcelService {
    ByteArrayInputStream load();

    List<TutorialEntity> getAllTutorials();

    void uploadFile(MultipartFile file);
}
