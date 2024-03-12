package co.edu.uptc.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import co.edu.uptc.model.User;

public class DocumentManagement {

    private static final String filePath = "src\\main\\java\\co\\edu\\uptc\\billing\\";
    private static final String fileExtension = ".pdf";

    public void generatePdf(String fileName, ArrayList<User> objects, User user, String value, String cardNumber)
            throws IOException {
        try (PdfWriter writer = new PdfWriter(new FileOutputStream(filePath + fileName + fileExtension))) {
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            // Add content
            String content = user.toStringPDF() +
                    "\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _" +
                    "\n" + cardNumber +
                    "\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  " +
                    "\n" + value;
            document.add(new Paragraph(content));

            document.close();
        }
    }
}
