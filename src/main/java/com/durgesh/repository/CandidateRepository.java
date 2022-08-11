package com.durgesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.durgesh.model.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Integer>{

}
