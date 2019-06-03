//JPDFWriter STARTS
package sample;


import java.awt.Graphics2D;
import java.awt.print.PageFormat;

import com.qoppa.pdfWriter.PDFDocument;
import com.qoppa.pdfWriter.PDFGraphics;
import com.qoppa.pdfWriter.PDFPage;

public class PDF
{
    public static void PdfFun()
    {
        try
        {
            // Create a document and a page in default Locale format
            PDFDocument pdfDoc = new PDFDocument();
            PDFPage newPage = pdfDoc.createPage(new PageFormat());
