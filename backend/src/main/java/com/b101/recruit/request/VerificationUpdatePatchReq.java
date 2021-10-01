package com.b101.recruit.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("VerificationUpdatePatchRequest")
public class VerificationUpdatePatchReq {
	
	@ApiModelProperty(name = "관리자 ID", example = "admin@ssafy.com")
	private String userEmail; // 관리자 확인용
	//검증 id
	@ApiModelProperty(name = "파일 ID", example = "1")
	private Long fileId; // 파일 ID? 검증 ID?
	//변경하고자하는 상태
	@ApiModelProperty(name = "검증 결과", example = "승인")
	private String verified;
	//반려사유
	@ApiModelProperty(name = "반려 사유")
	private String resonsRejection;
	
	
}
