package com.disderm.utils;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.printing.PDFPageable;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.w3c.dom.Document;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;

public class CreatePDF {

    public static final float FONT_SIZE= 12 ;
    public static final float FONT_SIZE_TITLE = 20;
    public static final float LEADING = 1.7f;
    public static final float MARGIN = 60;
    public static final PDFont FONT= PDType1Font.TIMES_ROMAN;
    public static final PDFont FONT_TITLE = PDType1Font.TIMES_BOLD;

    public static String getPDFLinkFromHTML( String html_to_pdf) {

        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        String writePath="";

        System.out.println(html_to_pdf);

        try {
            /* Definimos los ficheros temporales*/

            writePath = "F:\\pruebas\\";
            File hfile = new File(writePath + generatedString  + ".html");
            File pfile = new File(writePath + generatedString  + ".pdf");
            System.out.println("ruta " + hfile.toString() + " " + hfile);
            /* Escribimos el fichero temporal HTML*/
            try {
                PrintWriter out = new PrintWriter(hfile, "UTF-8");
                out.write(html_to_pdf);
                out.close();
                System.out.println(out);
            }
            catch  (Exception ex) {
                System.out.println("error en getPDFLinkFromHTML " + ex.getMessage());
                ex.printStackTrace();
            }

            /* Ejecutamos Wkhtmlpdf */
            Process p = Runtime.getRuntime().exec("xvfb-run wkhtmltopdf --enable-local-file-access --encoding utf-8 " + hfile.getPath() + " " + pfile.getPath());
            System.out.println("Este es el comando que no esta ejecutando ||||||||||||||||| xvfb-run wkhtmltopdf --enable-local-file-access --encoding utf-8 " + hfile.getPath() + " " + pfile.getPath());
            p.waitFor();

        } catch (Exception ex) {
            System.out.println("error en getPDFLinkFromHTML " + ex.getMessage());
            ex.printStackTrace();
        }


        return writePath+""+generatedString+".pdf";
    }

    public static void pdfEncryption(String ruta,String nombrePaciente)throws IOException{
        // step 1. Loading the pdf file
        File f = new File(ruta);
        PDDocument pdd = PDDocument.load(f);

        // step 2.Creating instance of AccessPermission
        // class
        AccessPermission ap = new AccessPermission();

        // step 3. Creating instance of
        // StandardProtectionPolicy
        StandardProtectionPolicy stpp
                = new StandardProtectionPolicy(nombrePaciente, nombrePaciente, ap);

        // step 4. Setting the length of Encryption key
        stpp.setEncryptionKeyLength(128);

        // step 5. Setting the permission
        stpp.setPermissions(ap);

        // step 6. Protecting the PDF file
        pdd.protect(stpp);

        // step 7. Saving and closing the the PDF Document
        pdd.save(ruta);
        pdd.close();

        System.out.println("PDF Encrypted successfully...");
    }

    public static void eliminarPDF(String urlDoc) throws IOException{
        File fichero = new File(urlDoc);
        System.out.println("La direccion del fichero es: " + fichero.toURI().toURL().toString());
        if(fichero.delete()){
            System.out.println("El archivo PDF ha sido eliminado");
        }else{
            System.out.println("El archivo PDF no ha sido eliminado");
        }
    }
}