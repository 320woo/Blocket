package com.b101.recruit.reponse;

import java.util.Date;

import com.b101.common.model.response.BaseResponseBody;
import com.b101.recruit.domain.entity.PersonalInfo;
import com.b101.recruit.domain.entity.Verification;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("VerificationResponse")
public class VerificationRes extends BaseResponseBody {
	//잘못만들었다~
	
	private Long id;// 검증 id
	private String userEmail;
	private String userName;
	private String Type; // 파일 타입 (자격증, 활동사항, 최종학력)
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date registrationDate; // 등록일 (personalInfo에서 파일들 등록 시에 verification 테이블 사용해서 직접 넣어주던가 OR 파일테이블에 등록날짜 칼럼 추가)
	
	private String currentStatus; // 현재상태(대기중, 승인완료, 거절)
	private String reasonsRejection; // 반려사유
	
	public static VerificationRes of(Integer statusCode, String message, VerificationRes verificationRes) {
		VerificationRes res = new VerificationRes();
		res.setStatusCode(statusCode);
		res.setMessage(message);
//		res.setId(VerificationRes.getId());
//		res.setPersonalinfo(verification.getPersonalinfo());
//		res.setRegistrationDate(verification.getRegistrationDate());
//		res.setCurrentStatus(verification.getCurrentStatus());
//		res.setReasonsRejection(verification.getReasonsRejection());
		return res;
	}

}
