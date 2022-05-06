package com.example.demo.service.excel;


import com.example.demo.data.jpa.entity.TutorialEntity;
import com.example.demo.data.jpa.repository.TutorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ExcelServiceImpl implements ExcelService {
    public static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    private final TutorialRepository tutorialRepository;
    private final ExcelHelper excelHelper;

    @Override
    public void uploadFile(MultipartFile file) {
        try {
            if (hasExcelFormat(file)) {
                List<TutorialEntity> tutorials = excelHelper.excelToTutorials(file.getInputStream());
                tutorialRepository.saveAll(tutorials);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ByteArrayInputStream load() {
        List<TutorialEntity> tutorials = tutorialRepository.findAll();
        return excelHelper.tutorialsToExcel(tutorials);
    }

    @Override
    public List<TutorialEntity> getAllTutorials() {
        return tutorialRepository.findAll();
    }

    private boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }
}
