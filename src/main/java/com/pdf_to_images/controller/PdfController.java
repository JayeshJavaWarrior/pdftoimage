<<<<<<< HEAD
package com.pdf_to_images.controller;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    private static final int DPI = 300; // DPI for rendering PDF to images
   
    @PostMapping("/convertToImages")
    public ResponseEntity<String> convertPdfToImages(@RequestParam("files") MultipartFile[] files) {
        try {
            // Create directory for storing images
            String outputDir = "C:\\Users\\Lenovo\\Desktop\\converted_images\\";
            File dir = new File(outputDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            for (MultipartFile file : files) {
                // Load PDF document
                PDDocument document = PDDocument.load(file.getInputStream());
                PDFRenderer renderer = new PDFRenderer(document);

                // Iterate over each page and convert to JPEG
                for (int pageIndex = 0; pageIndex < document.getNumberOfPages(); pageIndex++) {
                    BufferedImage image = renderer.renderImageWithDPI(pageIndex, DPI);
                    File outputFile = new File(outputDir + file.getOriginalFilename() + "_page_" + (pageIndex + 1) + ".jpg");
                    ImageIO.write(image, "jpg", outputFile);
                }

                document.close();
            }

            return ResponseEntity.ok("PDFs converted to images successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error converting PDFs to images.");
        }
    }

}
=======
package com.pdf_to_images.controller;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    private static final int DPI = 300; // DPI for rendering PDF to images
   
    @PostMapping("/convertToImages")
    public ResponseEntity<String> convertPdfToImages(@RequestParam("files") MultipartFile[] files) {
        try {
            // Create directory for storing images
            String outputDir = "C:\\Users\\Lenovo\\Desktop\\converted_images\\";
            File dir = new File(outputDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            for (MultipartFile file : files) {
                // Load PDF document
                PDDocument document = PDDocument.load(file.getInputStream());
                PDFRenderer renderer = new PDFRenderer(document);

                // Iterate over each page and convert to JPEG
                for (int pageIndex = 0; pageIndex < document.getNumberOfPages(); pageIndex++) {
                    BufferedImage image = renderer.renderImageWithDPI(pageIndex, DPI);
                    File outputFile = new File(outputDir + file.getOriginalFilename() + "_page_" + (pageIndex + 1) + ".jpg");
                    ImageIO.write(image, "jpg", outputFile);
                }

                document.close();
            }

            return ResponseEntity.ok("PDFs converted to images successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error converting PDFs to images.");
        }
    }

}
>>>>>>> 5ffac82676695c2944ea04d30ea3b7d4e8070a71
