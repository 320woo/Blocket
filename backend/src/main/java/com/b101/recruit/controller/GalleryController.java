package com.b101.recruit.controller;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.b101.recruit.service.impl.VerificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.b101.recruit.domain.dto.GalleryDto;
import com.b101.recruit.domain.entity.Gallery;
import com.b101.recruit.domain.entity.Verification;
import com.b101.recruit.reponse.GalleryRes;
import com.b101.recruit.service.impl.GalleryService;
import com.b101.recruit.service.impl.PersonalInfoService;
import com.b101.recruit.service.impl.S3Service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@AllArgsConstructor
@Api(value = "aws 연동 파일 처리 API", tags = { "Gallery." })
@RestController
@RequestMapping("/api/recruit/Gallery")
public class GalleryController {
	
	private S3Service s3Service;
	
	private GalleryService galleryService;

	private VerificationService vService;
	
	private PersonalInfoService pService;

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
							@PathVariable("personalInfoId") long pid, @PathVariable("sid") long sid, @PathVariable("sortation") String sortation) throws IOException, NoSuchAlgorithmException {

		// s3Service는 AWS S3의 비즈니스 로직을 담당하며, 파일을 조작
//		String imgPath = s3Service.upload(file);
		String imgPath = s3Service.upload(galleryDto.getFilePath(), file);
		galleryDto.setFilePath(imgPath);
		galleryDto.setPid(pid);
		galleryDto.setSid(sid);
		galleryDto.setSortation(sortation);

		// 파일을 해시값으로 변환하기 (SHA-256 알고리즘을 사용)
		MessageDigest mdSHA256 = MessageDigest.getInstance("SHA-256"); // NoSuchAlgorithmException 을 throws 해야 한다
		mdSHA256.update(file.getBytes()); // 파일 바이트로 messageDigest 를 갱신
		byte[] sha256hash = mdSHA256.digest(); // 해시 digest 의 반환 값은 byte 배열이므로 담을 변수를 선언

		// byte 배열을 16진수 문자열로 변환하여 표시
		StringBuilder hexSHA256hash = new StringBuilder();
		for (byte b : sha256hash) {
			String hexString = String.format("%02x", b);
			hexSHA256hash.append(hexString);
		}

		galleryDto.setTitle(String.valueOf(hexSHA256hash)); // 변환한 해시값을 title 에 담는다.

		// galleryService는 DB에 데이터를 조작하기 위한 서비스
		galleryService.savePost(galleryDto);

		vService.createVerification(galleryDto);

		return "redirect:/gallery";
	}
	
	@GetMapping("/galleryDetail/{galleryId}")
	@ApiOperation(value = "파일 상세조회", notes = "파일 정보를 불러온다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "토큰 인증 실패"),
		@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<GalleryRes> getFileDetail(@PathVariable(name = "galleryId") Long gId, @ApiIgnore Authentication authentication ) {
		System.out.println(authentication);
		Gallery gallery = galleryService.getGallery(gId).get();
		System.out.println(gallery.getFilePath());
		Object object = pService.getSortationDetail(gallery.getSortation(),gallery.getSid());
		Verification verification = vService.getVerification(gId);
		if(gallery!=null&&object!=null) return ResponseEntity.status(200).body(GalleryRes.of(gallery,object,verification));
		else return null;
	}
	
}
