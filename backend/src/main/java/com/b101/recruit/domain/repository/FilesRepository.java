package com.b101.recruit.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.b101.recruit.domain.entity.Files;

@Repository
public interface FilesRepository extends JpaRepository<Files, Long> {

	@Override
	List<Files> findAll();
	
}
