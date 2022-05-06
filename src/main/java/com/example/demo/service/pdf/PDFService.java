package com.example.demo.service.pdf;

import com.example.demo.data.jpa.entity.PDFEntity;
import com.example.demo.data.jpa.repository.PDFRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PDFService {
    private final PDFRepository pdfRepository;

    public ByteArrayResource downloadPDF(Long id) {
        PDFEntity pdfEntity = pdfRepository.findById(id).orElseThrow((() -> new RuntimeException("pdf not found for this ID")));

        return new ByteArrayResource(pdfEntity.getData());
    }

    public void uploadPDF(MultipartFile pdf) {

        byte[] fileContent = getFileContent(pdf);

        PDFEntity pdfEntity = new PDFEntity();
        pdfEntity.setData(fileContent);
        pdfEntity.setName("PDF");
        pdfRepository.save(pdfEntity);
    }

    private byte[] getFileContent(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new MultipartException("Something wrong with current file");
        }
    }
}