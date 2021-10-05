package com.b101.recruit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.b101.common.model.response.BaseResponseBody;
import com.b101.recruit.auth.CustomUserDetails;
import com.b101.recruit.domain.dto.VerificationDto;
import com.b101.recruit.domain.entity.User;
import com.b101.recruit.domain.entity.Verification;
import com.b101.recruit.reponse.VerificationListRes;
import com.b101.recruit.reponse.VerificationUpdatePatchRes;
import com.b101.recruit.request.VerificationListGetReq;
import com.b101.recruit.request.VerificationUpdatePatchReq;
import com.b101.recruit.service.impl.UserService;
import com.b101.recruit.service.impl.VerificationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "검증 API", tags = { "Verification." })
@RestController
@RequestMapping("/api/recruit/verification")
public class VerificationController {

	@Autowired
	UserService userService;
	
	@Autowired
	VerificationService verificationService;
	
	
	// 검증 상태 변경
	@PatchMapping("/certificate")
	@ApiOperation(value = "자격증 검증 상태 변경", notes = "자격증 검증 상태를 변경한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패"),
		@ApiResponse(code = 401, message = "로그인 인증 실패"),@ApiResponse(code = 403, message = "잘못된 요청")})
	public ResponseEntity<? extends BaseResponseBody> verifyCertificate(
			@RequestBody @ApiParam(value = "검증 정보", required = true) VerificationUpdatePatchReq vupr,
			@ApiIgnore Authentication authentication) {
		if (authentication == null) {
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
		} else {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
			String userEmail = userDetails.getUsername();

			User user = userService.findByUserEmail(userEmail);

//			if (user != null &&user.getType()==2) {
//				Verification verification = verificationService.updateVerification(vupr);
//				if(verification!=null)
//					return ResponseEntity.ok(VerificationUpdatePatchRes.of(200, "검증이 완료되었습니다.", verification));
//			}

			return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));
		}
	}
	
	// 검증 목록 조회
	@PostMapping("/list")
	@ApiOperation(value = "검증 목록 조회 조회", notes = "검증 목록을 조회한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패"),
		@ApiResponse(code = 401, message = "로그인 인증 실패"),@ApiResponse(code = 403, message = "잘못된 요청")})
	public ResponseEntity<? extends BaseResponseBody> getVerificationList(
			@RequestBody @ApiParam(value = "검증 정보", required = true) VerificationListGetReq verificationListGetReq
			,@ApiIgnore Authentication authentication
			) {
		if (authentication == null) {
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
		} else {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
			String userEmail = userDetails.getUsername();

			User user = userService.findByUserEmail("admin@ssafy.com");

			if (user != null &&user.getType()==2) {
				Page<VerificationDto> verList = verificationService.getVerificationList(verificationListGetReq);
				if(verList.isEmpty()) 
				return ResponseEntity.ok(VerificationListRes.of(200, "검증 목록 조회 완료.", verList));
			}

			return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));
		}
	}
	
//	// 활동내역 상태 변경
//	@PatchMapping("/activity")
//	@ApiOperation(value = "활동내역 검증 상태 변경", notes = "활동내역 검증 상태를 변경한다.")
//	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패"),
//		@ApiResponse(code = 401, message = "로그인 인증 실패"),@ApiResponse(code = 403, message = "잘못된 요청")})
//	public ResponseEntity<? extends BaseResponseBody> verifyActivity(
//			@RequestBody @ApiParam(value = "검증 정보", required = true) VerificationActivityPatchReq verificationActivityPatchReq,
//			@ApiIgnore Authentication authentication) {
//		if (authentication == null) {
//			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
//		} else {
//			CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
//			String userEmail = userDetails.getUsername();
//
//			User user = userService.findByUserEmail(userEmail);
//
//			if (user != null &&user.getType()==2) {
//				Activity activity = verificationService.verifyActivity(verificationActivityPatchReq);
//				if(activity!=null)
//				return ResponseEntity.ok(VerificationActivityPatchRes.of(200, "검증이 완료되었습니다.", activity));
//			}
//
//			return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));
//		}
//	}
//	
//	// 최종학력 상태 변경
//	@PatchMapping("/finalEducation")
//	@ApiOperation(value = "최종학력 검증 상태 변경", notes = "최종학력 검증 상태를 변경한다.")
//	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패"),
//		@ApiResponse(code = 401, message = "로그인 인증 실패"),@ApiResponse(code = 403, message = "잘못된 요청")})
//	public ResponseEntity<? extends BaseResponseBody> verifyFinalEducation(
//			@RequestBody @ApiParam(value = "검증 정보", required = true) VerificationFinalEducationPatchReq verificationFinalEducationPatchReq,
//			@ApiIgnore Authentication authentication) {
//		if (authentication == null) {
//			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
//		} else {
//			CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
//			String userEmail = userDetails.getUsername();
//
//			User user = userService.findByUserEmail(userEmail);
//
//			if (user != null &&user.getType()==2) {
//				FinalEducation finalEducation = verificationService.verifyFinalEducation(verificationFinalEducationPatchReq);
//				if(finalEducation!=null)
//					return ResponseEntity.ok(VerificationFinalEducationPatchRes.of(200, "검증이 완료되었습니다.", finalEducation));
//			}
//
//			return ResponseEntity.status(403).body(BaseResponseBody.of(403, "잘못된 요청입니다."));
//		}
//	}

//	// 검증 목록 조회
//	@GetMapping("/list")
//	@ApiOperation(value = "검증 목록 조회", notes = "검증 목록 정보를 응답한다.")
//	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
//			@ApiResponse(code = 404, message = "검증 정보 없음"), @ApiResponse(code = 500, message = "서버 오류") })
//	public ResponseEntity<Page<Verification>> getVerificationList(@ApiIgnore Authentication authentication,
//			VerificationListGetReq verificationListGetReq) {
//		if (authentication == null) {
//			return ResponseEntity.status(401).body(null);
//		} else {
//			CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
//			String userEmail = userDetails.getUsername();
//			User user = userService.findByUserEmail(userEmail);
//			// 관리자 검증은 어떻게 진행할것인지 고민해보기. 관리자역할에 대해 생각해보기
//
//			if (user != null) {
//				Page<Verification> verifications = verificationService.getVerifications(verificationListGetReq);
//				return ResponseEntity.ok(verifications);
//			}
//		}
//		//수정해야함
//		return ResponseEntity.status(404).body(null);
//	}
//
//	// 검증 상세 조회
//	@GetMapping("")
//	@ApiOperation(value = "검증 등록", notes = "검증 정보를 등록한다.")
//	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패") })
//	public ResponseEntity<? extends BaseResponseBody> createConference(
//			@RequestBody @ApiParam(value = "검증 정보", required = true) VerificationDetailGetReq verificationDetailGetReq,
//			@ApiIgnore Authentication authentication) {
//		if (authentication == null) {
//			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
//		} else {
//			CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
//			String userEmail = userDetails.getUsername();
//
//			User user = userService.findByUserEmail(userEmail);
//
//			if (user != null) {
//				Verification verification = verificationService.getVerificationDetail(verificationDetailGetReq);
//				return ResponseEntity.ok(VerificationRes.of(200, "success", verification));
//			}
//
//		}
//		return null;
//	}
//
//	// 검증 등록
//	@PostMapping("")
//	@ApiOperation(value = "검증 등록", notes = "검증 정보를 등록한다.")
//	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패") })
//	public ResponseEntity<? extends BaseResponseBody> createConference(
//			@RequestBody @ApiParam(value = "검증 정보", required = true) VerificationCreatePostReq verificationCreatePostReq,
//			@ApiIgnore Authentication authentication) {
//		if (authentication == null) {
//			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
//		} else {
//			CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
//			String userEmail = userDetails.getUsername();
//
//			User user = userService.findByUserEmail(userEmail);
//
//			if (user != null) {
//				Verification verification = verificationService.createVerification(verificationCreatePostReq);
//				return ResponseEntity.ok(VerificationRes.of(200, "success",verification));
//			}
//
//		}
//		return null;
//	}
//
//	// 검증 상태 변경
//	@PatchMapping("")
//	@ApiOperation(value = "검증 등록", notes = "검증 정보를 등록한다.")
//	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 400, message = "실패") })
//	public ResponseEntity<? extends BaseResponseBody> createConference(
//			@RequestBody @ApiParam(value = "검증 정보", required = true) VerificationUpdatePatchReq verificationUpdatePatchReq,
//			@ApiIgnore Authentication authentication) {
//		if (authentication == null) {
//			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
//		} else {
//			CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
//			String userEmail = userDetails.getUsername();
//
//			User user = userService.findByUserEmail(userEmail);
//
//			if (user != null) {
//				Verification verification = verificationService.updateVerification(verificationUpdatePatchReq);
//				return ResponseEntity.ok(VerificationUpdatePatchRes.of(200, "success", verification));
//			}
//
//		}
//		return null;
//	}

}
