package com.b101.recruit.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("VerificationCetificatePatchRequest")
public class VerificationCetificatePatchReq {

	private String userEmail; // 관리자 확인용
	private Long certificateId;
	private Long verified;
	
}
