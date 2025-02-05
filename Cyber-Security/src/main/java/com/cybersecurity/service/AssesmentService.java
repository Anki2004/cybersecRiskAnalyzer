package com.cybersecurity.service;

import com.cybersecurity.model.Assesment;
import com.cybersecurity.model.Threat;
import com.cybersecurity.model.Vulnerability;
import com.cybersecurity.repository.AssesmentRepository;
import com.cybersecurity.repository.ThreatRepository;
import com.cybersecurity.repository.VulnerabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssesmentService {
    @Autowired
    private AssesmentRepository assesmentRepository;
    @Autowired
    private VulnerabilityRepository vulnerabilityRepository;
    @Autowired
    private ThreatRepository threatRepository;

    public Assesment createAssesment(Assesment assesment){
        Vulnerability vulnerability = vulnerabilityRepository.findById(assesment.getVulnerability().getId()).orElseThrow(()-> new IllegalArgumentException("Invalid vulnerability ID"));
        Threat threat = threatRepository.findById(assesment.getThreat().getId()).orElseThrow(()-> new IllegalArgumentException("Invalid Threat ID"));
        assesment.setVulnerability(vulnerability);
        assesment.setThreat(threat);
        assesment.setRiskScore(calculateRiskScore(vulnerability.getSevertiy(), threat.getLikelihood()));
        return assesmentRepository.save(assesment);
    }

    public List<Assesment> getAssesmentByUser(Long userId){
        return assesmentRepository.findByUserId(userId);
    }
    private double calculateRiskScore(int severity, int likelihood){
        return severity*likelihood;
    }

}
