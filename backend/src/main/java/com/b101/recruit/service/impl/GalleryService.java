package com.b101.recruit.service.impl;

import org.springframework.stereotype.Service;

import com.b101.recruit.domain.dto.GalleryDto;
import com.b101.recruit.domain.repository.GalleryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GalleryService {

	private GalleryRepository galleryRepository;
	
	public void savePost(GalleryDto galleryDto) {
		galleryRepository.save(galleryDto.toEntity());
	}
	
}
