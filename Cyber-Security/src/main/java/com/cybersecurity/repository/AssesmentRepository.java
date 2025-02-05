package com.cybersecurity.repository;

import com.cybersecurity.model.Assesment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssesmentRepository extends JpaRepository<Assesment, Long> {
    List<Assesment> findByUserId(Long userId);
}
