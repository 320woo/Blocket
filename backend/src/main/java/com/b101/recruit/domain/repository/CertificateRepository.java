package com.b101.recruit.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.b101.recruit.domain.entity.Certificate;
import com.google.common.base.Optional;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {

//	Optional<List<Certificate>> findByPersonalInfo_id(Long personal_info_id);
}
