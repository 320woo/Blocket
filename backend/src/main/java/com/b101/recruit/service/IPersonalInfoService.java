package com.b101.recruit.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.b101.recruit.domain.entity.Activity;
import com.b101.recruit.domain.entity.Certificate;
import com.b101.recruit.domain.entity.PersonalInfo;
import com.b101.recruit.reponse.PersonalInfoPostRes;
import com.b101.recruit.request.ActivityPostReq;
import com.b101.recruit.request.CertificatePostReq;
import com.b101.recruit.request.PersonalInfoPostReq;

public interface IPersonalInfoService {

	PersonalInfo createPersonalInfo(PersonalInfoPostReq personalinfoPostReq, MultipartFile[] files) throws IllegalStateException, IOException;
	
	PersonalInfoPostRes getonePersonalInfo(Long id, String email);
	
	PersonalInfo updatePersonalInfo(Long id, PersonalInfoPostReq personalinfoPostReq);
	
	void deletePersonalInfo(Long id);
	
	long getAllPersonalInfoCount();
	
	List<PersonalInfoPostReq> getAllPersonalInfo(Pageable pageable, String email);
	
	Certificate createCertificate(Long id, CertificatePostReq certificate);
	
	Certificate updateCertificate(Long pId, Long cId, CertificatePostReq certificate);
	
	void deleteCertificate(Long pId, Long cId);
	
	Activity createActivity(Long id, ActivityPostReq activity);
	
	Activity updateActivity(Long pId, Long aId, ActivityPostReq activity);
	
	void deleteActivity(Long pId, Long aId);
	
}
