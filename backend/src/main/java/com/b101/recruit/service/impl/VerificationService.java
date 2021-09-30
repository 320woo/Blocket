package com.b101.recruit.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.b101.recruit.domain.entity.Activity;
import com.b101.recruit.domain.entity.Certificate;
import com.b101.recruit.domain.entity.FinalEducation;
import com.b101.recruit.domain.repository.ActivityRepository;
import com.b101.recruit.domain.repository.CertificateRepository;
import com.b101.recruit.domain.repository.FinalEducationRepository;
import com.b101.recruit.request.VerificationActivityPatchReq;
import com.b101.recruit.request.VerificationCetificatePatchReq;
import com.b101.recruit.request.VerificationFinalEducationPatchReq;
import com.b101.recruit.service.IVerificationService;

@Service("verificationService")
public class VerificationService implements IVerificationService {

	@Autowired
	CertificateRepository certificateRepository;
	
	@Autowired
	ActivityRepository activityRepository;
	
	@Autowired
	FinalEducationRepository finalEducationRepository;
	
	@Override
	public Certificate verifyCertificate(VerificationCetificatePatchReq verificationCetificatePatchReq) {
		Optional<Certificate> certificate = certificateRepository.findById(verificationCetificatePatchReq.getCertificateId());
		if(certificate.isPresent()) {
			certificate.get().setVerified(verificationCetificatePatchReq.getVerified());
			return certificateRepository.save(certificate.get());
		}
		return null;
	}

	@Override
	public Activity verifyActivity(VerificationActivityPatchReq verificationActivityPatchReq) {
		Optional<Activity> activity = activityRepository.findById(verificationActivityPatchReq.getActivityId());
		if(activity.isPresent()) {
			activity.get().setVerified(verificationActivityPatchReq.getVerified());
			return activityRepository.save(activity.get());
		}
		return null;
	}

	@Override
	public FinalEducation verifyFinalEducation(VerificationFinalEducationPatchReq verificationFinalEducationPatchReq) {
		Optional<FinalEducation> finalEducation = finalEducationRepository.findById(verificationFinalEducationPatchReq.getFinalEducationId());
		if(finalEducation.isPresent()) {
			finalEducation.get().setVerified(verificationFinalEducationPatchReq.getVerified());
			return finalEducationRepository.save(finalEducation.get());
		}
		return null;
	}

//	
//	@Autowired
//	JpaVerificationRepository jpaVerificationRepository;
//	
//	public Page<Verification> getVerifications(VerificationListGetReq verificationListGetReq) {
//		int page = verificationListGetReq.getPage();
//		int size = verificationListGetReq.getSize();
//		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "registrationDate"));
//		Page<Verification> VerificationList = verificationRepository.findAll(pageable);
//		
//		return VerificationList;
//	}
//
//	@Override
//	public Verification updateVerification(VerificationUpdatePatchReq verificationUpdatePatchReq) {
//		Optional<Verification> verification = jpaVerificationRepository.updateVerification(verificationUpdatePatchReq);
//		if(verification.isPresent()) return verification.get();
//		return null;
//	}
//
//	@Override
//	public Verification createVerification(VerificationCreatePostReq verificationCreatePostReq) {
//		//어떤 식으로 로직을 구성해야할지 감을 못잡겠다,,
//		Verification verification = new Verification();
//		verification.setId(verificationCreatePostReq.getId());
//		verification.setPersonalinfo(verificationCreatePostReq.getPersonalinfo());
//		verification.setRegistrationDate(verificationCreatePostReq.getRegistrationDate());
//		verification.setCurrentStatus(verificationCreatePostReq.getCurrentStatus());
//		verification.setReasonsRejection(verificationCreatePostReq.getReasonsRejection());
//		verificationRepository.save(verification);
//		if(verification.isPresent()) return verification.get();
//		return null;
//	}
//	
//	@Override
//	public Verification getVerificationDetail(VerificationDetailGetReq verificationDetailGetReq) {
//		Optional<Verification> verification = verificationRepository.findById(verificationDetailGetReq.getId());
//		if(verification.isPresent()) return verification.get();
//		return null;
//	}

}
