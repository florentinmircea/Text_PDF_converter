package com.itextpdf.pdf;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.TextAlignment;

import javax.swing.*;
import java.io.*;

public class Processing {
    void save(String f, JTextArea ta) throws Exception {
        try {
            PrintWriter pw = new PrintWriter(
                    new FileWriter(new File(f)));
            pw.println(ta.getText());
            pw.close();
        } catch (Exception e) {
            throw new Exception("Error occurred while saving");
        }
    }

    void open(String f, JTextArea ta) throws Exception {
        try {
            if (!f.equals("nullnull")) {
                ta.setText("");
                BufferedReader bf =
                        new BufferedReader(
                                new FileReader(new File(f)));
                String l;
                l = bf.readLine();
                while (l != null) {
                    ta.append(l + "\n");
                    l = bf.readLine();
                }
            }
        } catch (Exception e) {
            throw new Exception("Error opening");
        }
    }

    void clear(JTextArea ta) {
        ta.selectAll();
        ta.replaceSelection("");
    }

    void convert(String f, JTextArea ta) {
        try {
            PdfWriter writer = new PdfWriter(new File(f + ".pdf"));
            PdfDocument pdfDocument = new PdfDocument(writer);

            pdfDocument.addNewPage();
            Document document = new Document(pdfDocument, PageSize.A4);

            int i = 0;
            for (String line : ta.getText().split("\\n")) {
                i++;
                Paragraph p = new Paragraph(line);
                p.setTextAlignment(TextAlignment.JUSTIFIED);
                if (i < 34)
                    document.add(p);
                else {
                    document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                    i = 0;
                }
            }
            document.close();
            JOptionPane.showMessageDialog(null, "Conversion complete!", "Converted", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
