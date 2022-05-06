package com.example.demo.service.excel;

import com.example.demo.data.jpa.entity.TutorialEntity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public interface ExcelHelper {
    ByteArrayInputStream tutorialsToExcel(List<TutorialEntity> tutorials);

    List<TutorialEntity> excelToTutorials(InputStream inputStream);
}
