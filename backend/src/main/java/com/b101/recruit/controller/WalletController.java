package com.b101.recruit.controller;

import com.b101.common.model.response.BaseResponseBody;
import com.b101.recruit.service.impl.UserWalletService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "유저 API", tags = { "UserWallet." })
@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    @Autowired
    UserWalletService userWalletService;

//    @PostMapping("")
//    @ApiOperation(value = "지갑 정보 등록", notes = "지갑을 등록한다")
//    @ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "토큰 인증 실패"),
//            @ApiResponse(code = 500, message = "서버 오류") })
//    public ResponseEntity<BaseResponseBody> createWallet(@RequestBody )
}
