package com.cybersecurity.controller;

import com.cybersecurity.model.Assesment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cybersecurity.service.AssesmentService;

import java.util.List;

@RestController
@RequestMapping("/api/assesments")
public class AssesmentController {
    @Autowired
    private AssesmentService assesmentService;

    @PostMapping
    public ResponseEntity<Assesment> createAssesment(@RequestBody Assesment assesment){
        Assesment createdAssesment = assesmentService.createAssesment(assesment);
        return ResponseEntity.ok(createdAssesment);
    }
    @GetMapping("/{user_id}")
    public ResponseEntity<List<Assesment>> getAssesmentByUser(@PathVariable Long userId){
        List<Assesment> assesmentList = assesmentService.getAssesmentByUser(userId);
        return ResponseEntity.ok(assesmentList);
    }
}
