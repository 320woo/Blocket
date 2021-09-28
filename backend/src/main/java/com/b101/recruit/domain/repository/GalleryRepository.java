package com.b101.recruit.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.b101.recruit.domain.entity.Gallery;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {

}
