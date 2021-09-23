package com.b101.recruit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.b101.common.model.response.BaseResponseBody;
import com.b101.common.util.JwtTokenUtil;
import com.b101.recruit.auth.CustomUserDetails;
import com.b101.recruit.domain.entity.User;
import com.b101.recruit.reponse.UserLoginPostRes;
import com.b101.recruit.request.PasswordUpdatePatchReq;
import com.b101.recruit.request.UserLoginPostReq;
import com.b101.recruit.request.UserRegisterPostReq;
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
	public ResponseEntity<? extends BaseResponseBody> register(@RequestBody UserRegisterPostReq registerInfo) {
		try {
			userService.createUser(registerInfo);
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
			@RequestBody @ApiParam(value = "로그인 정보", required = true) UserLoginPostReq loginInfo) {
		String userId = loginInfo.getEmail();
		String password = loginInfo.getPassword();

		User user = userService.findByUserId(userId);
		System.out.println(user.getEmail());
		if (user != null) {
			if (passwordEncoder.matches(password, user.getPassword())) {
				return ResponseEntity.ok(UserLoginPostRes.of(200, "로그인에 성공하였습니다.", JwtTokenUtil.createToken(user.getEmail())));
			}
		}
		return ResponseEntity.status(404).body(UserLoginPostRes.of(404, "아이디 또는 비밀번호가 일치하지 않습니다.", null));
	}
	// 회원 가입에 필요한 아이디 중복체크
	// 로그인한 회원의 정보 조회
	// 로그인된 회원의 회원 탈퇴
	
	// 로그인된 회원의 회원정보 수정
	// 로그인된 회원의 비밀번호 수정
	// 로그인된 회원의 비밀번호 수정
		@PatchMapping("/me/password")
		@ApiOperation(value = "회원 비밀번호 수정", notes = "회원 비밀번호 수정")
		@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
				@ApiResponse(code = 404, message = "수정 실패"), @ApiResponse(code = 500, message = "서버 오류") })
		public ResponseEntity<BaseResponseBody> patchPassword(@ApiIgnore Authentication authentication,
				@RequestBody @ApiParam(value = "수정 정보") PasswordUpdatePatchReq passwordUpdatePatchReq) {

			if (authentication == null) {
				return ResponseEntity.status(404).body(BaseResponseBody.of(401, "로그인 인증 실패"));
			} else {
				CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
				String userId = userDetails.getUsername();
				
				User user = userService.findByUserId(userId);
//				long user = userService.updatePassword(userId, passwordUpdatePatchReq);
				
				if (user != null) {
					if (passwordEncoder.matches(passwordUpdatePatchReq.getPassword(), user.getPassword())) {
						long result = userService.updatePassword(userId, passwordUpdatePatchReq.getNewPassword());
						if(result > 0)
							return ResponseEntity.status(200).body(BaseResponseBody.of(200, "수정이 완료됐습니다!"));
					}
				}
				return ResponseEntity.status(404).body(BaseResponseBody.of(404, "수정이 실패됐습니다!"));
			}
		}
	
	// 이메일 확인
	// 로그인된 회원의 비밀번호 찾기
	
}
