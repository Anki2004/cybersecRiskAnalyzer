package com.cybersecurity.service;

import com.cybersecurity.model.Assesment;
import com.cybersecurity.repository.AssesmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.ByteArrayOutputStream;

@Service
public class ReportService {
    @Autowired
    private AssesmentRepository assesmentRepository;
    public byte[] generateReport(Long assesmentId){
        Assesment assesment = assesmentRepository.findById(assesmentId).orElseThrow(()->new IllegalArgumentException("Invalid assesment ID"));
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PDDocument document = new PDDocument()){
            PDPage page = new PDPage();
            document.addPage(page);
            try(PDPageContentStream contentStream = new PDPageContentStream(document, page)){
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 170);
                contentStream.showText("Cyber Security Risk Assesment Report");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 650);
                contentStream.showText("Assesment ID: "+assesment.getId());
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("User: "+assesment.getUser().getUsername());
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Vulnerability: "+assesment.getVulnerability().getDescription());
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Threat: " + assesment.getThreat().getDescription());
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Risk Score: " + assesment.getRiskScore());
                contentStream.endText();


            }
            document.save(outputStream);
            return outputStream.toByteArray();
        }catch (Exception e){
            throw new RuntimeException("Error generating report" + e);
        }
    }

}
