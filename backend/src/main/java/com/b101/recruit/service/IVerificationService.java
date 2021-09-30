package com.b101.recruit.service;


import com.b101.recruit.domain.entity.Activity;
import com.b101.recruit.domain.entity.Certificate;
import com.b101.recruit.domain.entity.FinalEducation;
import com.b101.recruit.request.VerificationActivityPatchReq;
import com.b101.recruit.request.VerificationCetificatePatchReq;
import com.b101.recruit.request.VerificationFinalEducationPatchReq;

public interface IVerificationService {

	Certificate verifyCertificate(VerificationCetificatePatchReq verificationCetificatePutReq);
	Activity verifyActivity(VerificationActivityPatchReq verificationActivityPutReq);
	FinalEducation verifyFinalEducation(VerificationFinalEducationPatchReq verificationFinalEducationPatchReq);
//	Page<Verification> getVerifications(VerificationListGetReq verificationListGetReq);
//	Verification createVerification(VerificationCreatePostReq verificationCreatePostReq);
//	Verification getVerificationDetail(VerificationDetailGetReq verificationDetailGetReq);
//	Verification updateVerification(VerificationUpdatePatchReq verificationUpdatePatchReq);

}
