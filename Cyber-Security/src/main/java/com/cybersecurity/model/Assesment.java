package com.cybersecurity.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity

@Table(name = "assesments")
public class Assesment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "Vulnerability", nullable = false)
    private Vulnerability vulnerability;

    public Vulnerability getVulnerability() {
        return vulnerability;
    }

    public void setVulnerability(Vulnerability vulnerability) {
        this.vulnerability = vulnerability;
    }

    @ManyToOne
    @JoinColumn(name = "threat_id", nullable = false)
    private Threat threat;

    @Column(name = "risk_score")
    private double riskScore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Threat getThreat() {
        return threat;
    }

    public void setThreat(Threat threat) {
        this.threat = threat;
    }

    public double getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(double riskScore) {
        this.riskScore = riskScore;
    }

//    public double getSeverity() {
//        return 0;
//    }
//
//    public double getLikelihood() {
//        return 0;
//    }


}
