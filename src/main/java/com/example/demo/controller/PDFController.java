package com.example.demo.controller;

import com.example.demo.service.pdf.PDFExporterServiceImpl;
import com.example.demo.service.pdf.PDFService;
import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.MediaType.APPLICATION_PDF_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/PDF")
public class PDFController {
    private final PDFService pdfService;
    private final PDFExporterServiceImpl pdfExporterService;


    @PostMapping
    public void uploadPDF(@RequestParam MultipartFile file) {
        pdfService.uploadPDF(file);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> downloadPDF(@PathVariable Long id) {
        ByteArrayResource byteArrayResource = pdfService.downloadPDF(id);

        return ResponseEntity
                .ok()
                .header(CONTENT_DISPOSITION, "attachment; filename=DownloadedPDF")
                .body(byteArrayResource);
    }

    @GetMapping("/export")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType(APPLICATION_PDF_VALUE);
        String headerValue = "attachment; filename=users_" + LocalDate.now() + ".pdf";
        response.setHeader(CONTENT_DISPOSITION, headerValue);
        pdfExporterService.export(response);
    }
}
