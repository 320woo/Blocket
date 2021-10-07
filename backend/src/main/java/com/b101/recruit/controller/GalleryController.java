package com.b101.recruit.controller;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.b101.common.model.response.BaseResponseBody;
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

    @PostMapping("/saveInDB")
	public Gallery createGallery(@RequestBody GalleryDto galleryDto) {
		SimpleDateFormat date = new SimpleDateFormat("yyyymmddHHmmss");
		String fileName = galleryDto.getFilePath() + date.format(new Date());
		galleryDto.setFilePath(fileName);

		// DB에 저장한 객체를 반환한다. 이 객체를 반환받아, 다시 S3에 파일로 저장하는 걸 진행해야 한다.
		Gallery result = galleryService.savePost(galleryDto);

		// 파일을 저장하였으므로, 검증 객체를 새로 생성한다.
		vService.createVerification(result);
		return result;
	}


	@PostMapping("/{galleryId}/S3Upload")
	public ResponseEntity<BaseResponseBody> execWrite(@RequestBody MultipartFile file, @PathVariable("galleryId") Long gid) throws IOException, NoSuchAlgorithmException {
		System.out.println(file.toString());

		Gallery gallery = galleryService.getGallery(gid).get();
		GalleryDto galleryDto = new GalleryDto();
		galleryDto.setPid(gallery.getPid());
		galleryDto.setSid(gallery.getSid());
		galleryDto.setFilePath(gallery.getFilePath());
		galleryDto.setSortation(gallery.getSortation());
		galleryDto.setId(gallery.getId());

		System.out.println("서버에 올릴 파일 이름은..." + gallery.getFilePath().toString());

		// s3Service는 AWS S3의 비즈니스 로직을 담당하며, 파일을 조작
		String imgPath = s3Service.upload(galleryDto.getFilePath(), file);

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
		// 변경사항 저장하기
		galleryService.savePost(galleryDto);

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "성공적으로 파일을 업로드하였습니다."));
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
