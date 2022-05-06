package com.example.demo.service.pdf;

import com.example.demo.data.jpa.entity.TutorialEntity;
import com.example.demo.data.jpa.repository.TutorialRepository;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PDFExporterServiceImpl {
    private final TutorialRepository tutorialRepository;

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        document.addTitle("Export Tutorials");

        Font font = FontFactory.getFont(FontFactory.TIMES_ITALIC);
        font.setSize(17);
        font.setColor(Color.BLACK);

        Paragraph p = new Paragraph("List of Tutorials", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(90f);
        table.setWidths(new float[]{1.8f, 2.5f, 2.5f, 1.8f, 2.5f});
        table.setSpacingBefore(10);
        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();

    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.GREEN);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("Tutorial ID", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Title", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Published", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        final List<TutorialEntity> tutorials = tutorialRepository.findAll();
        for (TutorialEntity tutorial : tutorials) {
            table.addCell(String.valueOf(tutorial.getId()));
            table.addCell(tutorial.getTitle());
            table.addCell(tutorial.getDescription());
            table.addCell(String.valueOf(tutorial.isPublished()));
            table.addCell(LocalDate.now().toString());
        }
    }
}