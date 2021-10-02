package com.b101.recruit.reponse;

import java.util.Date;

import com.b101.common.model.response.BaseResponseBody;
import com.b101.recruit.domain.entity.Verification;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("VerificationUpdatePatchResponse")
public class VerificationUpdatePatchRes extends BaseResponseBody {
	
	private Long verificationId;
	private Long personalInfoId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date registrationDate;
	private String currentStatus;
	private String resonsRejection;

	public static VerificationUpdatePatchRes of(Integer statusCode, String message, Verification verification) {
		VerificationUpdatePatchRes res = new VerificationUpdatePatchRes();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setVerificationId(verification.getId());
		res.setPersonalInfoId(verification.getPersonalinfo().getId());
		res.setRegistrationDate(verification.getRegistrationDate());
		res.setCurrentStatus(verification.getCurrentStatus());
		res.setResonsRejection(verification.getReasonsRejection());
		return res;
	}
}
