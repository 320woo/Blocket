package com.b101.recruit.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.b101.recruit.service.impl.VerificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.b101.recruit.domain.dto.GalleryDto;
import com.b101.recruit.service.impl.GalleryService;
import com.b101.recruit.service.impl.S3Service;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@Api(value = "aws 연동 파일 처리 API", tags = { "Gallery." })
@RestController
@RequestMapping("/api/recruit/Gallery")
public class GalleryController {
	
	private S3Service s3Service;
	
	private GalleryService galleryService;

	private VerificationService vService;

	@GetMapping("/gallery")
    public String dispWrite(Model model) {
        List<GalleryDto> galleryDtoList = galleryService.getList();

        model.addAttribute("galleryList", galleryDtoList);

        return "/gallery";
    }
	
	@PostMapping("{personalInfoId}/gallery/{sid}/{sortation}")	// edu, act, cert
	// form으로부터 넘어온 파일 객체를 받기 위해, MultipartFile 타입의 파라미터를 작성
	public String execWrite(GalleryDto galleryDto,
							MultipartFile file,
							@PathVariable("personalInfoId") long pid, @PathVariable("sid") long sid, @PathVariable("sortation") String sortation) throws IOException {

		// s3Service는 AWS S3의 비즈니스 로직을 담당하며, 파일을 조작
//		String imgPath = s3Service.upload(file);
		String imgPath = s3Service.upload(galleryDto.getFilePath(), file);
		galleryDto.setFilePath(imgPath);
		galleryDto.setPid(pid);
		galleryDto.setSid(sid);
		galleryDto.setSortation(sortation);
		
		// galleryService는 DB에 데이터를 조작하기 위한 서비스
		galleryService.savePost(galleryDto);

//		vService.createVerification(galleryDto);

		return "redirest:/gallery";
	}
	
}
