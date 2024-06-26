package com.pdf_to_images.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api1/pdf1")
public class PdfController1 {
	
	private static final int DPI = 300;
  //  private static final long MAX_FILE_SIZE = 300 * 1024 * 1024; // 300 MB in bytes

    @PostMapping("/convertToImages")
    public ResponseEntity<String> convertPdfToImages(@RequestParam("files") MultipartFile[] files) {
        try {
            String outputDir = "C:\\Users\\Lenovo\\Desktop\\converted_images1\\";
            File dir = new File(outputDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            for (MultipartFile file : files) {
                         PDDocument document = PDDocument.load(file.getInputStream());
                PDFRenderer renderer = new PDFRenderer(document);

                for (int pageIndex = 0; pageIndex < document.getNumberOfPages(); pageIndex++) {
                    BufferedImage image = renderer.renderImageWithDPI(pageIndex, DPI);
                  File outputFile = new File(outputDir + file.getOriginalFilename() + "page" + (pageIndex + 1) + ".jpg");
                // File outputFile = new File(outputDir +  "page" + (pageIndex + 1) + ".jpg");
  
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
