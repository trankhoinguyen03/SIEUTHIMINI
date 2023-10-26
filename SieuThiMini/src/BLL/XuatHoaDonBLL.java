package BLL;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;


import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JTable;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Desktop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class XuatHoaDonBLL {

	
	
	public void exportToPDF(JTable table,String amountPaid,String change,String maKH,String tenKH,String ngay) {
        // Create a file chooser to select the export location
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save PDF");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            
            File outputFile = fileChooser.getSelectedFile();

            // Append the ".pdf" extension if not already present
            String filePath = outputFile.getAbsolutePath();
            if (!filePath.endsWith(".pdf")) {
                filePath += ".pdf";
                outputFile = new File(filePath);
            }

            Document document = new Document(PageSize.A4);

            try {
            	PdfWriter.getInstance(document, new FileOutputStream(outputFile));
            	document.open();
            	
            	Paragraph ten = new Paragraph("Ten khach hang: " + tenKH);
            	document.add(ten);
            	
            	Paragraph ngay1 = new Paragraph("Ngay xuat: " + ngay);
            	document.add(ngay1);
            	// Set the title
            	com.itextpdf.text.Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            	Paragraph title = new Paragraph("Hoa Don", titleFont);
            	title.setAlignment(Element.ALIGN_CENTER);
            	title.setSpacingBefore(20);
            	document.add(title);

            	// Add spacing between title and table
            	Paragraph spacing = new Paragraph(" ");
            	document.add(spacing);

            	PdfPTable pdfTable = new PdfPTable(5);
            	pdfTable.setWidthPercentage(100);

            	pdfTable.addCell("STT");
            	pdfTable.addCell("Ten San Pham");
            	pdfTable.addCell("Ma San Pham");
            	pdfTable.addCell("So Luong");
            	pdfTable.addCell("Gia");

            	int rowCount = table.getRowCount();
            	int temp = 1;
            	for (int i = 0; i < rowCount; i++) {
            	    String tensp = table.getValueAt(i, 0).toString();
            	    String masp = table.getValueAt(i, 1).toString();
            	    String soluong = table.getValueAt(i, 2).toString();
            	    String gia = table.getValueAt(i, 3).toString();
            	    pdfTable.addCell(temp++ + "");
            	    pdfTable.addCell(tensp);
            	    pdfTable.addCell(masp);
            	    pdfTable.addCell(soluong);
            	    pdfTable.addCell(gia);
            	}

            	document.add(pdfTable);
            	
            	
            	Paragraph amountPaidParagraph = new Paragraph("Tien khach dua: " + amountPaid);
            	document.add(amountPaidParagraph);

            	Paragraph changeParagraph = new Paragraph("Tien thoi lai: " + change);
            	document.add(changeParagraph);
            	document.close();


                System.out.println("PDF exported successfully.");

                // Open the exported PDF file
                Desktop.getDesktop().open(outputFile);
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
                System.out.println("Failed to export PDF.");
            }
        }
    }

}
