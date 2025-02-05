package com.cybersecurity.controller;

import com.cybersecurity.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/{assesment_id}")
    public ResponseEntity<byte[]>generateReport(@PathVariable Long assesmentId){
        byte[] reportData = reportService.generateReport(assesmentId);
        if(reportData != null){
            return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=/'report.pdf'").body(reportData);

        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
