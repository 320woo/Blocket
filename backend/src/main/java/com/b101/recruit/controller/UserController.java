package com.b101.recruit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.b101.common.model.response.BaseResponseBody;
import com.b101.common.util.JwtTokenUtil;
import com.b101.recruit.domain.entity.User;
import com.b101.recruit.reponse.UserLoginPostRes;
import com.b101.recruit.request.UserLoginPostReq;
import com.b101.recruit.request.UserRegisterPostReq;
import com.b101.recruit.service.impl.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
}
