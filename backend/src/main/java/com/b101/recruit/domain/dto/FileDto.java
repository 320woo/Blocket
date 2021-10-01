package com.b101.recruit.domain.dto;

import com.b101.recruit.domain.entity.Files;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FileDto {
	
//	private String savefolder;
//	private String originfile;
//	private String savefile;
	
	private Long id;
	private String title;
	private String filePath;
	private String imgFullPath;
	
	public Files toEntity(){
        Files build = Files.builder()
                .id(id)
                .title(title)
                .filePath(filePath)
                .build();
        return build;
    }

    @Builder
    public FileDto(Long id, String title, String filePath, String imgFullPath) {
        this.id = id;
        this.title = title;
        this.filePath = filePath;
        this.imgFullPath = imgFullPath;
    }
    
}
