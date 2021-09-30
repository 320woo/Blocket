package com.b101.recruit.controller;

import com.b101.common.model.response.BaseResponseBody;
import com.b101.recruit.reponse.UserWalletRes;
import com.b101.recruit.reponse.UserWalletUpdatePatchRes;
import com.b101.recruit.request.UserWalletRegisterPostReq;
import com.b101.recruit.request.UserWalletUpdatePatchReq;
import com.b101.recruit.service.impl.UserWalletService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "유저 지갑 API", tags = { "UserWallet." })
@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    @Autowired
    UserWalletService userWalletService;

    // 지갑 생성
    @PostMapping("/register")
    @ApiOperation(value = "지갑 정보 등록", notes = "지갑을 등록한다")
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "실패"),
            @ApiResponse(code = 500, message = "서버 오류") })
    public ResponseEntity<? extends BaseResponseBody> createWallet(@RequestBody UserWalletRegisterPostReq userWalletRegisterPostReq) {
        try {
            userWalletService.createUserWallet(userWalletRegisterPostReq);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "지갑 생성에 성공하였습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "지갑 생성에 실패하였습니다."));
        }
    }

    // 지갑 조회
    @GetMapping("/me")
    @ApiOperation(value = "지갑 정보 조회", notes = "지갑 정보를 조회한다")
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "실패"),
            @ApiResponse(code = 500, message = "서버 오류") })
    public ResponseEntity<? extends BaseResponseBody> getWalletInfo() {
        // 지갑이 존재하지 않는다면
        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "지갑이 존재하지 않습니다."));

        // 존재한다면
//        return ResponseEntity.ok(UserWalletRes.of(200, userWallet));
    }

    // 지갑 수정
    @PatchMapping("/me")
    @ApiOperation(value = "지갑 정보 수정", notes = "지갑 정보를 수정한다")
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "실패"),
            @ApiResponse(code = 500, message = "서버 오류") })
    public ResponseEntity<UserWalletUpdatePatchRes> patchUserWallet(@RequestBody UserWalletUpdatePatchReq userWalletUpdatePatchReq) {
        return null;
    }

    // 지갑 삭제
}
