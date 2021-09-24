package com.b101.recruit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.b101.common.model.response.BaseResponseBody;
import com.b101.common.util.JwtTokenUtil;
import com.b101.recruit.auth.CustomUserDetails;
import com.b101.recruit.domain.entity.User;
import com.b101.recruit.reponse.UserLoginPostRes;
import com.b101.recruit.reponse.UserRes;
import com.b101.recruit.reponse.UserUpdatePatchRes;
import com.b101.recruit.request.PasswordUpdatePatchReq;
import com.b101.recruit.request.UserLoginPostReq;
import com.b101.recruit.request.UserRegisterPostReq;
import com.b101.recruit.request.UserUpdatePatchReq;
import com.b101.recruit.service.impl.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "유저 API", tags = { "User." })
@RestController
@RequestMapping("/api/recruit/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	// 회원 가입
	@PostMapping("/register")
	@ApiOperation(value = "회원가입", notes = "사용자의 정보를 입력하여 회원가입한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "success"), @ApiResponse(code = 401, message = "fail"),
			@ApiResponse(code = 404, message = "url error"), @ApiResponse(code = 500, message = "server error") })
	public ResponseEntity<? extends BaseResponseBody> register(@RequestBody UserRegisterPostReq userRegisterPostReq) {
		try {
			userService.createUser(userRegisterPostReq);
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "회원가입에 성공하였습니다."));
		} catch (Exception e) {
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "회원가입에 실패하였습니다."));
		}
	}
	
	// 로그인
	@PostMapping("/login")
	@ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "로그인 성공", response = UserLoginPostRes.class) })
	public ResponseEntity<UserLoginPostRes> login(
			@RequestBody @ApiParam(value = "로그인 정보", required = true) UserLoginPostReq userLoginPostReq) {
		String userEmail = userLoginPostReq.getEmail();
		String password = userLoginPostReq.getPassword();

		User user = userService.findByUserEmail(userEmail);
		System.out.println(user.getEmail());
		if (user != null) {
			if (passwordEncoder.matches(password, user.getPassword())) {
				return ResponseEntity.ok(UserLoginPostRes.of(200, "로그인에 성공하였습니다.", JwtTokenUtil.createToken(user.getEmail())));
			}
		}
		return ResponseEntity.status(404).body(UserLoginPostRes.of(404, "아이디 또는 비밀번호가 일치하지 않습니다.", null));
	}
	
	// 회원 가입에 필요한 아이디 중복체크
	@GetMapping("{users_id}")
	@ApiOperation(value = "아이디 중복체크", notes = "존재하는 아이디인지 아닌지 확인한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "회원가입 가능한 아이디입니다."),
			@ApiResponse(code = 404, message = "url error"), @ApiResponse(code = 409, message = "이미 존재하는 사용자 아이디입니다."),
			@ApiResponse(code = 500, message = "server error") })
	public ResponseEntity<? extends BaseResponseBody> confirmUserId(@PathVariable("users_id") String userEmail) {
		if (!userService.confirmUserEmail(userEmail)) {
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, "회원가입 가능한 아이디입니다."));
		}
		return ResponseEntity.status(409).body(BaseResponseBody.of(409, "이미 존재하는 사용자 아이디입니다."));
	}
	
	// 로그인한 회원의 정보 조회
	@GetMapping("/me")
	@ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseResponseBody> getUserInfo(@ApiIgnore Authentication authentication) {
		if (authentication == null) {
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
		} else {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
			String userEmail = userDetails.getUsername();
			User user = userService.findByUserEmail(userEmail);
			if (user != null)
				return ResponseEntity.ok(UserRes.of(200,user));				
		}
		return ResponseEntity.status(404).body(BaseResponseBody.of(404, "존재하지 않는 회원입니다."));
	}
	
	// 로그인된 회원의 회원 탈퇴
	@DeleteMapping("/me")
	@ApiOperation(value = "회원 정보 삭제", notes = "회원 정보 삭제")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "탈퇴 실패"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseResponseBody> deleteUser(@ApiIgnore Authentication authentication) {
		if (authentication == null) {
			return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
		} else {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
			String userEmail = userDetails.getUsername();
			long user = userService.deleteUser(userEmail);
			if (user > 0) {
				return ResponseEntity.ok(BaseResponseBody.of(200, "탈퇴되었습니다."));
			} else {
				return ResponseEntity.status(404).body(BaseResponseBody.of(404, "탈퇴 실패하였습니다."));
			}
		}
	}
	
	// 로그인된 회원의 회원정보 수정
	@PatchMapping("/me")
	@ApiOperation(value = "회원 정보 수정", notes = "회원 정보 수정")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "수정 실패"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<UserUpdatePatchRes> patchUser(@ApiIgnore Authentication authentication,
			@RequestBody @ApiParam(value = "수정 정보") UserUpdatePatchReq UserUpdatePatchReq) {
		if (authentication == null) {
			return ResponseEntity.status(401).body(UserUpdatePatchRes.of(401, "로그인 인증 실패"));
		} else {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
			String userEmail = userDetails.getUsername();
			long user = userService.updateUser(userEmail, UserUpdatePatchReq);
			if (user > 0) {
				return ResponseEntity.ok(UserUpdatePatchRes.of(200, "수정이 완료됐습니다."));
			} else {
				return ResponseEntity.status(404).body(UserUpdatePatchRes.of(404, "수정 실패하였습니다."));
			}
		}
	}
	
	// 로그인된 회원의 비밀번호 수정
		@PatchMapping("/me/password")
		@ApiOperation(value = "회원 비밀번호 수정", notes = "회원 비밀번호 수정")
		@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
				@ApiResponse(code = 404, message = "수정 실패"), @ApiResponse(code = 500, message = "서버 오류") })
		public ResponseEntity<BaseResponseBody> patchPassword(@ApiIgnore Authentication authentication,
				@RequestBody @ApiParam(value = "수정 정보") PasswordUpdatePatchReq passwordUpdatePatchReq) {

			if (authentication == null) {
				return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 인증 실패"));
			} else {
				CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
				String userEmail = userDetails.getUsername();
				
				User user = userService.findByUserEmail(userEmail);
//				long result = userService.updatePassword(userId, passwordUpdatePatchReq);
				
				if (user != null) {
					if (passwordEncoder.matches(passwordUpdatePatchReq.getPassword(), user.getPassword())) {
						long result = userService.updatePassword(user.getEmail(), passwordUpdatePatchReq.getNewPassword());
						if(result > 0)
							return ResponseEntity.ok(BaseResponseBody.of(200, "수정이 완료됐습니다."));
					}
				}
				return ResponseEntity.status(404).body(BaseResponseBody.of(404, "수정 실패했습니다."));
			}
		}
	
		
		
	// 이메일 확인
	// 로그인된 회원의 비밀번호 찾기
	
}
