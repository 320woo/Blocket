package com.b101.recruit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLConnection;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.b101.common.model.response.BaseResponseBody;
import com.b101.recruit.auth.CustomUserDetails;
import com.b101.recruit.domain.entity.Certificate;
import com.b101.recruit.domain.entity.PersonalInfo;
import com.b101.recruit.reponse.PersonalInfoPostRes;
import com.b101.recruit.request.CertificatePostReq;
import com.b101.recruit.request.PersonalInfoPostReq;
import com.b101.recruit.service.impl.PersonalInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "신상정보 API", tags = { "PersonalInfo." })
@RestController
@RequestMapping("/api/recruit/personalinfo")
public class PersonalInfoController {

	@Autowired
	private PersonalInfoService service;
	
	// 저장할 기본 디렉토리 설정 application.property 파일에 설정하고 가져온다.
	@Value("${server.tomcat.basedir}")
	private String basedir;
	
	@PostMapping()
	@ApiOperation(value = "신상정보 등록", notes = "기본 신상정보를 등록한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "토큰 인증 실패"),
		@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<BaseResponseBody> createPersonalInfo(@RequestBody PersonalInfoPostReq personalinfoPostReq,
			@RequestPart(value = "file", required = false) MultipartFile[] files) {
		try {
			PersonalInfo personalinfo = service.createPersonalInfo(personalinfoPostReq, files);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Fail"));
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(BaseResponseBody.of(500, "Fail"));
		}
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@GetMapping("/{personalinfoId}")
	@ApiOperation(value = "신상정보 상세내용", notes = "신상정보의 상세 내용을 확인한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "토큰 인증 실패"),
		@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<PersonalInfoPostRes> getonePersonalInfo(@PathVariable(name = "personalinfoId") Long id, @ApiIgnore Authentication authentication) {
		CustomUserDetails userDetails = (CustomUserDetails)authentication.getDetails();
		String email = userDetails.getName();
		PersonalInfoPostRes personalinfo = service.getonePersonalInfo(id, email);
		return ResponseEntity.status(200).body(personalinfo);
	}
	
	@GetMapping("/pic")
	@ApiOperation(value = "신상정보에 등록된 사진 정보")
	public void housepic(@RequestParam("sfolder") String sfolder, @RequestParam("ofile") String ofile,
			@RequestParam("sfile") String sfile, HttpServletResponse response) {
		String origin = basedir + File.separator + sfolder + File.separator + sfile;
		try {
			File down = new File(origin);
			FileInputStream is = new FileInputStream(down);// 서버에 저장된 파일 읽어서

			response.setContentType("application/octet-stream"); // 모든 content-type을 구성할 수 있는 설정
			// 헤더 설정 전송 인코딩
			response.setHeader("Content-Disposition", "attachment; filename=\"" + ofile + "\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			// 이름을 통해 콘텐트 타입을 가져온다.
			String mimeType = URLConnection.guessContentTypeFromName(origin);
			response.setContentType(mimeType);
			// 콘텐츠 길이를 설정
			response.setContentLengthLong(down.length());
			// 데이터 전송
			ServletOutputStream os = response.getOutputStream();
			FileCopyUtils.copy(is, os);
			os.flush();
			is.close();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PutMapping("/{personalinfoId}")
	@ApiOperation(value = "신상정보 수정", notes = "신상정보를 수정한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "토큰 인증 실패"),
		@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<BaseResponseBody> updatePersonalInfo(@PathVariable(name = "personalinfoId") Long id,
			@RequestBody PersonalInfoPostReq personalinfoPostReq) {
		PersonalInfo result = service.updatePersonalInfo(id, personalinfoPostReq);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@DeleteMapping("/{personalinfoId}")
	@ApiOperation(value = "신상정보 삭제", notes = "신상정보를 삭제한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "토큰 인증 실패"),
		@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<BaseResponseBody> deletePersonalInfo(@PathVariable(name = "personalinfoId") Long id) {
		service.deletePersonalInfo(id);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@GetMapping("/allCount")
	@ApiOperation(value = "신상정보 전체 카운트", notes = "페이지 구성")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "토큰 인증 실패"),
		@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<Long> getAllPersonalInfoCount() {
		long size = service.getAllPersonalInfoCount();
		return ResponseEntity.status(200).body(size);
	}
	
	@GetMapping("/all")
	@ApiOperation(value = "신상정보 전체 목록", notes = "페이지랑 한페이지에 들어갈 사이즈 >> ?size=10&page=1")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "토큰 인증 실패"),
		@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<List<PersonalInfoPostReq>> getAllPersonalInfo(Pageable pageable, @ApiIgnore Authentication authentication) {
		CustomUserDetails userDetails = (CustomUserDetails)authentication.getDetails();
		String email = userDetails.getName();
		List<PersonalInfoPostReq> personalinfoList = service.getAllPersonalInfo(pageable, email);
		return ResponseEntity.status(200).body(personalinfoList);
	}
	
	@PostMapping("/{personalinfoId}/certificate")
	@ApiOperation(value = "어학, 자격증 등록", notes = "어학, 자격증을 등록한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "토큰 인증 실패"),
		@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<BaseResponseBody> createCertificate(@PathVariable(name = "personalinfoId") Long id,
			@RequestBody CertificatePostReq certificate) {
		Certificate certificate2 = service.createCertificate(id, certificate);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@PutMapping("/{personalinfoId}/{certificateId}")
	@ApiOperation(value = "어학, 자격증 수정", notes = "어학, 자격증을 수정한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "토큰 인증 실패"),
		@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<Certificate> updateCertificate(@PathVariable(name = "personalinfoId") Long pId,
			@PathVariable(name = "certificateId") Long cId, @RequestBody CertificatePostReq certificate) {
		Certificate certificate2 = service.updateCertificate(pId, cId, certificate);
		return ResponseEntity.status(200).body(certificate2);
	}
	
	@DeleteMapping("/{personalinfoId}/{certificateId}")
	@ApiOperation(value = "어학, 자격증 삭제", notes = "어학, 자격증을 삭제한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "토큰 인증 실패"),
		@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<BaseResponseBody> deleteCertificate(@PathVariable(name = "personalinfoId") Long pId,
			@PathVariable(name = "certificateId") Long cId) {
		service.deleteCertificate(pId, cId);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
}
