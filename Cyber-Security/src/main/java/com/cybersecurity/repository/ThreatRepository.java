package com.cybersecurity.repository;

import com.cybersecurity.model.Threat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThreatRepository extends JpaRepository<Threat, Long> {
}
