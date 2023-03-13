package com.example.pidev1.Service;

import java.awt.Color;
        import java.io.IOException;
        import java.util.List;

        import javax.servlet.http.HttpServletResponse;

import com.example.pidev1.Entity.Student;
import com.lowagie.text.*;
        import com.lowagie.text.pdf.*;


public class StudentPDFExporter {
    private List<Student> listStudent;

    public StudentPDFExporter(List<Student> listStudent ){
        this.listStudent = listStudent;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("User ID", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Full Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Roles", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("test", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Student user : listStudent) {
            table.addCell(String.valueOf(user.getIdStudent()));
            table.addCell(user.getEmail());
            table.addCell(user.getContact());
            table.addCell(user.getLastname());
            table.addCell(user.getFirstname());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Users", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}