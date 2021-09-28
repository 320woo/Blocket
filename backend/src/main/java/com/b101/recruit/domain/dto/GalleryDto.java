package com.b101.recruit.domain.dto;

import com.b101.recruit.domain.entity.Gallery;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Setter;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GalleryDto {

	private Long id;
	private String title;
	private String filePath;
	
	public Gallery toEntity(){
	  Gallery build = Gallery.builder()
	        .id(id)
	        .title(title)
	        .filePath(filePath)
	        .build();
	  return build;
	}

	@Builder
	public GalleryDto(Long id, String title, String filePath) {
	  this.id = id;
	  this.title = title;
	  this.filePath = filePath;
	}
	
}
