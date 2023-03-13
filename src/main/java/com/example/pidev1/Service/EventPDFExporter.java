package com.example.pidev1.Service;

import com.example.pidev1.Entity.Event;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;


import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class EventPDFExporter {

    public static byte[] export(List<Event> events) throws DocumentException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();

        // Create a table with 6 columns
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Add table headers
        addTableHeader(table);

        // Add table rows
        for (Event event : events) {
            addTableRow(table, event);
        }

        document.add(table);
        document.close();

        return baos.toByteArray();
    }

    private static void addTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(new java.awt.Color(BaseColor.LIGHT_GRAY.getRGB()));
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(java.awt.Color.WHITE);


        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Type", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Place", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);
    }

    private static void addTableRow(PdfPTable table, Event event) {
        table.addCell(event.getIdEvent().toString());
        table.addCell(event.getNameEvent());
        table.addCell(event.getType());
        table.addCell(event.getDescription());
        table.addCell(event.getPlace());
        table.addCell(event.getDate().toString());
    }
}
