package com.b101.recruit.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.b101.recruit.domain.entity.Verification;

//@Repository
public interface VerificationRepository extends JpaRepository<Verification, Long>{

	Optional<Verification> findByFileId(Long fileId);

	Page<Verification> findByCurrentStatusContaining(Pageable pageable, String verified);
}