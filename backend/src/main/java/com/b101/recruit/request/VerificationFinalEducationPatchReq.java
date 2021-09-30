package com.b101.recruit.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("VerificationFinalEducationPatchRequest")
public class VerificationFinalEducationPatchReq {

	private String userEmail; // 관리자 확인용
	private Long finalEducationId;
	private Long verified;
}
