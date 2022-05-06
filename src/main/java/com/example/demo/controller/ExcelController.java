package com.example.demo.controller;

import com.example.demo.data.jpa.entity.TutorialEntity;
import com.example.demo.service.excel.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/excel")
public class ExcelController {


    private final ExcelService fileService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        fileService.uploadFile(file);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/tutorials")
    public ResponseEntity<List<TutorialEntity>> getAllTutorials() {
        return ResponseEntity.ok(fileService.getAllTutorials());
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> getFile() {
        InputStreamResource file = new InputStreamResource(fileService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = tutorials.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}