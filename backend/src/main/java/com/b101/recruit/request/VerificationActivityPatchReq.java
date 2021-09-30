package com.b101.recruit.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("VerificationActivityPatchRequest")
public class VerificationActivityPatchReq {

	private String userEmail; // 관리자 확인용
	private Long activityId;
	private Long verified;

}
