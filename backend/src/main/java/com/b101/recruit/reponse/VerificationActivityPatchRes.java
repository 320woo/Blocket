package com.b101.recruit.reponse;

import com.b101.common.model.response.BaseResponseBody;
import com.b101.recruit.domain.entity.Activity;
import com.b101.recruit.domain.entity.Certificate;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("VerificationActivityPatchResponse")
public class VerificationActivityPatchRes extends BaseResponseBody {

	private Long id;
	private String name; // 이름
	private String activity; // 활동 구분
	private String period; // 기간
	private String description; // 설명
	private String userEmail;
	private Long verified; // 검증 여부. 0 : 대기, 1 : 승인, 2 :거부

	public static VerificationActivityPatchRes of(Integer statusCode, String message, Activity activity) {
		VerificationActivityPatchRes res = new VerificationActivityPatchRes();
		res.setStatusCode(statusCode);
		res.setMessage(message);
		res.setId(activity.getId());
		res.setName(activity.getName());
		res.setActivity(activity.getActivity());
		res.setPeriod(activity.getPeriod());
		res.setDescription(activity.getDescription());
		res.setUserEmail(activity.getUserEmail());
		res.setVerified(activity.getVerified());

		return res;
	}
}
