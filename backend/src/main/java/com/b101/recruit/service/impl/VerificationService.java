package com.b101.recruit.service.impl;

import java.util.Optional;

import com.b101.recruit.domain.dto.GalleryDto;
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
import com.b101.recruit.domain.entity.Gallery;
import com.b101.recruit.domain.entity.PersonalInfo;
import com.b101.recruit.domain.entity.Verification;
import com.b101.recruit.domain.repository.ActivityRepository;
import com.b101.recruit.domain.repository.CertificateRepository;
import com.b101.recruit.domain.repository.FinalEducationRepository;
import com.b101.recruit.domain.repository.PersonalInfoRepository;
import com.b101.recruit.domain.repository.VerificationRepository;
import com.b101.recruit.request.VerificationListGetReq;
import com.b101.recruit.request.VerificationUpdatePatchReq;
import com.b101.recruit.service.IVerificationService;

@Service("verificationService")
public class VerificationService implements IVerificationService {

	@Autowired
	VerificationRepository verificationRepository;

//	@Autowired
//	JpaVerificationRepository jpaVerificationRepository;
	
	@Autowired
	PersonalInfoRepository personalInfoRepository;

	@Override
	public Verification createVerification(GalleryDto galleryDto) throws NullPointerException {
		
		Verification verification = new Verification();
		Gallery gallery = new Gallery(galleryDto.getId(), galleryDto.getTitle(), galleryDto.getFilePath(), galleryDto.getPid(), galleryDto.getSid(), galleryDto.getSortation());
//		verification.setPersonalinfo(galleryDto.getPid());
		PersonalInfo personalInfo = personalInfoRepository.findById(gallery.getPid()).get();
		verification.setPersonalinfo(personalInfo);
		verification.setCurrentStatus("승인대기");
		verification.setGallery(gallery);
		Long userId = personalInfoRepository.findUserIdById(gallery.getPid());
		verification.setUserId(userId); 
		return verificationRepository.save(verification);
	}

	@Override
	public Verification updateVerification(VerificationUpdatePatchReq vcpr) {
		Optional<Verification> verification = verificationRepository.findByGalleryId(vcpr.getFileId());
		if (verification.isPresent()) {
			String status = vcpr.getVerified();
			verification.get().setCurrentStatus(status);
			verification.get().setReasonsRejection(vcpr.getReasonsRejection());
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

	@Override
	public String getCurrentStatus(Long gId) {
		return verificationRepository.findByGalleryId(gId).get().getCurrentStatus();
	}

}
