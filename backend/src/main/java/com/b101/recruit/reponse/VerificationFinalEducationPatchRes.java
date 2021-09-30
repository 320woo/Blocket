package com.b101.recruit.reponse;

import com.b101.common.model.response.BaseResponseBody;
import com.b101.recruit.domain.entity.Activity;
import com.b101.recruit.domain.entity.FinalEducation;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("VerificationFinalEducationPatchResponse")
public class VerificationFinalEducationPatchRes extends BaseResponseBody {
	private Long id;
	private String name; // 최종 학교 이름
	private String sortation; // 학교 구분
	private String grades; // 성적

	private String userEmail;
	private Long verified; // 검증 여부. 0 : 대기, 1 : 승인, 2 :거부

	public static VerificationFinalEducationPatchRes of(Integer statusCode, String message,
			FinalEducation finalEducation) {
		VerificationFinalEducationPatchRes res = new VerificationFinalEducationPatchRes();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setId(finalEducation.getId());
		res.setName(finalEducation.getName());
		res.setSortation(finalEducation.getSortation());
		res.setGrades(finalEducation.getGrades());
		res.setUserEmail(finalEducation.getUserEmail());
		res.setVerified(finalEducation.getVerified());

		return res;
	}

}
