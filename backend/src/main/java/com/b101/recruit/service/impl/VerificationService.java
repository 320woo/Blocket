package com.b101.recruit.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.b101.recruit.domain.dto.VerificationDto;
import com.b101.recruit.domain.entity.Activity;
import com.b101.recruit.domain.entity.Certificate;
import com.b101.recruit.domain.entity.File;
import com.b101.recruit.domain.entity.FinalEducation;
import com.b101.recruit.domain.entity.Verification;
import com.b101.recruit.domain.repository.ActivityRepository;
import com.b101.recruit.domain.repository.CertificateRepository;
import com.b101.recruit.domain.repository.FinalEducationRepository;
import com.b101.recruit.domain.repository.JpaVerificationRepository;
import com.b101.recruit.domain.repository.PersonalInfoRepository;
import com.b101.recruit.domain.repository.VerificationRepository;
import com.b101.recruit.request.ActivityPostReq;
import com.b101.recruit.request.CertificatePostReq;
import com.b101.recruit.request.FinalEducationPostReq;
import com.b101.recruit.request.VerificationListGetReq;
import com.b101.recruit.request.VerificationUpdatePatchReq;
import com.b101.recruit.service.IVerificationService;

@Service("verificationService")
public class VerificationService implements IVerificationService {

	@Autowired
	CertificateRepository certificateRepository;

	@Autowired
	ActivityRepository activityRepository;

	@Autowired
	FinalEducationRepository finalEducationRepository;

	@Autowired
	VerificationRepository verificationRepository;

	@Autowired
	JpaVerificationRepository jpaVerificationRepository;
	
	@Autowired
	PersonalInfoRepository personalInfoRepository;

	@Override
	public Verification createVerification(File file) throws NullPointerException {
		Verification verification = new Verification();
		verification.setPersonalinfo(file.getPersonalInfo());
		verification.setCurrentStatus("승인대기");
		verification.setFile(file);
		Long userId = personalInfoRepository.findUserIdById(file.getPersonalInfo().getId());
		verification.setUserId(userId); 
		return verificationRepository.save(verification);
	}

	@Override
	public Verification updateVerification(VerificationUpdatePatchReq vcpr) {
		Optional<Verification> verification = verificationRepository.findByFileId(vcpr.getFileId());
		if (verification.isPresent()) {
			String status = vcpr.getVerified();
			verification.get().setCurrentStatus(status);
			if (status.equals("거절")) verification.get().setReasonsRejection(vcpr.getResonsRejection());
			return verificationRepository.save(verification.get());
		}
		return null;
	}

	@Override
	public Page<VerificationDto> getVerificationList(VerificationListGetReq vlgr) {
		int size = vlgr.getSize();
		int page = vlgr.getPage() - 1;
		String verified = vlgr.getVerified();
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "registrationDate"));
		if (verified.equals("")) {
			Page<Verification> pageTuts = verificationRepository.findAll(pageable);
			Page<VerificationDto> verList = pageTuts.map(Verification -> new VerificationDto());
			return verList;
		} else {
			Page<Verification> pageTuts = verificationRepository.findByCurrentStatusContaining(pageable,verified);
			Page<VerificationDto> verList = pageTuts.map(Verification -> new VerificationDto());
			return verList;
		}
	}

	//	@Override
//	public Certificate verifyCertificate(VerificationCetificatePatchReq vcpr) {
//		Optional<Certificate> certificate = certificateRepository.findById(vcpr.getCertificateId());
//		if(certificate.isPresent()) {
////			certificate.get().setVerified(verificationCetificatePatchReq.getVerified());
//			return certificateRepository.save(certificate.get());
//		}
//		return null;
//	}
//
//	@Override
//	public Activity verifyActivity(VerificationActivityPatchReq vapr) {
//		Optional<Activity> activity = activityRepository.findById(vapr.getActivityId());
//		if(activity.isPresent()) {
////			activity.get().setVerified(verificationActivityPatchReq.getVerified());
//			return activityRepository.save(activity.get());
//		}
//		return null;
//	}
//
//	@Override
//	public FinalEducation verifyFinalEducation(VerificationFinalEducationPatchReq vfpr) {
//		Optional<FinalEducation> finalEducation = finalEducationRepository.findById(vfpr.getFinalEducationId());
//		if(finalEducation.isPresent()) {
////			finalEducation.get().setVerified(verificationFinalEducationPatchReq.getVerified());
//			return finalEducationRepository.save(finalEducation.get());
//		}
//		return null;
//	}

//	@Override
//	public Page<VerificationDto> getVerificationList(VerificationListGetReq verificationListGetReq) {
//		int page = verificationListGetReq.getPage();
//		int size = verificationListGetReq.getSize();
//		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "registrationDate"));
//		Page<Verification> VerificationList = verificationRepository.findAll(pageable);
//		
//		return VerificationList;
//	}

	/*
	 * 1. 자격증/활동사항/최종학력 테이블 & Req/Res들에 userId 넣기 2. 각각 객체 createCetificate 요놈들에
	 * setUserId(getUserId) 추가 3. personalInfo 입력할 떼 verification
	 */
//	@Override
//	public void createVerification(FinalEducation fe) {
//		Verification verification = new Verification();
//		verification.setPersonalinfo(fe.getPersonalinfo());
//		verification.setCurrentStatus("승인대기");
//		verification.setUserId(fe.getUserId());
//		verificationRepository.save(verification);
//	}
//	@Override
//	public void createVerification(Activity act) {
//		Verification verification = new Verification();
//		verification.setPersonalinfo(act.getPersonalinfo());
//		verification.setCurrentStatus("승인대기");
//		verification.setUserId(act.getUserId());
//		verificationRepository.save(verification);
//	}
//	
//	@Override
//	public void createVerification(Certificate cer) {
//		Verification verification = new Verification();
//		verification.setPersonalinfo(cer.getPersonalinfo());
//		verification.setCurrentStatus("승인대기");
//		verification.setUserId(cer.getUserId());
//		verificationRepository.save(verification);
//	}
	
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
