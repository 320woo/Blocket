package com.b101.recruit.reponse;

import com.b101.common.model.response.BaseResponseBody;
import com.b101.recruit.domain.entity.Certificate;
import com.b101.recruit.domain.entity.UserWallet;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("VerificationCetificatePutResponse")
public class VerificationCetificatePatchRes extends BaseResponseBody {

	private Long id;
	private String userEmail;
	private String name; // 자격증, 어학점수명
	private String sortation; // 구분
	private String acquisitionDate; // 취득기간
	private String score; // 점수, 등급
	private Long verified;

	public static VerificationCetificatePatchRes of(Integer statusCode, String message, Certificate certificate) {
		VerificationCetificatePatchRes res = new VerificationCetificatePatchRes();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setId(certificate.getId());
		res.setUserEmail(certificate.getUserEmail());
		res.setName(certificate.getName());
		res.setSortation(certificate.getSortation());
		res.setAcquisitionDate(certificate.getAcquisitionDate());
		res.setScore(certificate.getScore());
		res.setVerified(certificate.getVerified());

		return res;
	}

}
