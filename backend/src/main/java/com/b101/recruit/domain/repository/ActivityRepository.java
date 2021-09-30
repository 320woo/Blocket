package com.b101.recruit.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.b101.recruit.domain.entity.Activity;
import com.google.common.base.Optional;

//@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{

	Optional<List<Activity>> findByPersonalinfo_id(Long personal_info_id);
	
}
