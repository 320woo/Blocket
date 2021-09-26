package com.b101.recruit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import com.b101.recruit.domain.entity.Certificate;
import com.b101.recruit.domain.entity.PersonalInfo;
import com.b101.recruit.domain.entity.User;
import com.b101.recruit.domain.repository.CertificateRepository;
import com.b101.recruit.domain.repository.PersonalInfoRepository;
import com.b101.recruit.domain.repository.UserRepository;
import com.b101.recruit.reponse.PersonalInfoPostRes;
import com.b101.recruit.request.CertificatePostReq;
import com.b101.recruit.request.PersonalInfoPostReq;
import com.b101.recruit.service.IPersonalInfoService;

@Service("PersonalInfoService")
public class PersonalInfoService implements IPersonalInfoService {

	@Autowired
	PersonalInfoRepository personalinfoRepository;
	
	@Autowired
	CertificateRepository certificateRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	// 저장할 기본 디렉토리 설정 application.property 파일에 설정하고 가져온다.
	@Value("${server.tomcat.basedir}")
	private String basedir;
	
	@Override
	@Transactional
	public PersonalInfo createPersonalInfo(PersonalInfoPostReq personalinfoPostReq, MultipartFile[] files)
			throws IllegalStateException, IOException {
		PersonalInfo personalinfo = new PersonalInfo();
		User user = userService.findByUserEmail(personalinfoPostReq.getEmail());
		personalinfo.setUser(user); // 유저 셋팅
		personalinfo.setDateBirth(personalinfoPostReq.getDateBirth());
		personalinfo.setAddress(personalinfoPostReq.getAddress());
		personalinfo.setEnglishName(personalinfoPostReq.getEnglishName());
		personalinfo.setGender(personalinfoPostReq.getGender());
		personalinfo.setRepProfile(personalinfoPostReq.getRepProfile());
		personalinfo.setMilitaryService(personalinfoPostReq.getMilitaryService());
		personalinfo.setVeteransAffairs(personalinfoPostReq.getVeteransAffairs());
		personalinfo.setFinalEducation(personalinfoPostReq.getFinalEducation());
		personalinfo.setDisabled(personalinfoPostReq.getDisabled());
		personalinfo.setTranscript(personalinfoPostReq.getTranscript());
		personalinfo.setIntExtAct(personalinfoPostReq.getIntExtAct());
		personalinfo = personalinfoRepository.save(personalinfo);
		
		// 파일 처리
//		if(files != null) {
//			String realPath = basedir;
//			// 오늘날짜로 폴더 설정
//			String today = new SimpleDateFormat("yyMMdd").format(new Date());
//			// 실제 저장하는 폴더주소
//			String saveFolder = realPath + File.separator + today;
//			// 저장 폴더 주소를 불러왔는데
//			File folder = new File(saveFolder);
//			// 폴더가 존재하지 않으면 폴더 생성
//			if (!folder.exists())
//				folder.mkdirs();
//			for (MultipartFile f : files) {
//				// 원래 파일의 이름
//				String originalFileName = f.getOriginalFilename();
//				if (!originalFileName.isEmpty()) {
//					// 랜덤한 파일이름으로 가상 파일 이름을 생성시켜준다. subString을 통해서 확장자까지 저장
//					String saveFileName = UUID.randomUUID().toString()
//							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
//					f.transferTo(new File(folder, saveFileName));
//					PersonalInfo_File pfile = new PersonalInfo_File();
//					pfile.setSavefolder(saveFolder);
//					pfile.setPersonalInfo(personalinfo);
//					pfile.setOriginfile(originalFileName);
//					pfile.setSavefile(saveFileName);
//					fileRepository.save(pfile);
//				}
//			}
//		}
		return personalinfo;
	}
	
	@Override
	public PersonalInfoPostRes getonePersonalInfo(Long id, String email) {
		PersonalInfo personalinfo = personalinfoRepository.findById(id).get();
		String u = userRepository.findByEmail(email).get().getEmail();
		PersonalInfoPostRes p = new PersonalInfoPostRes();
		p.setId(personalinfo.getId());
		p.setDateBirth(personalinfo.getDateBirth());
		p.setAddress(personalinfo.getAddress());
		p.setEnglishName(personalinfo.getEnglishName());
		p.setGender(personalinfo.getGender());
		p.setRepProfile(personalinfo.getRepProfile());
		p.setMilitaryService(personalinfo.getMilitaryService());
		p.setVeteransAffairs(personalinfo.getVeteransAffairs());
		p.setFinalEducation(personalinfo.getFinalEducation());
		p.setDisabled(personalinfo.getDisabled());
		p.setTranscript(personalinfo.getTranscript());
		p.setIntExtAct(personalinfo.getIntExtAct());
		
		// 파일 처리
//		List<PersonalInfo_File> list = fileRepository.findByPersonalInfo_id(id).get();
//		if (list != null) {
//			List<FileDto> copy = new ArrayList<>();
//			for (PersonalInfo_File fi : list) {
//				FileDto dto = new FileDto();
//				dto.setOriginfile(fi.getOriginfile());
//				dto.setSavefile(fi.getSavefile());
//				dto.setSavefolder(fi.getSavefolder());
//				copy.add(dto);
//			}
//			p.setFiles(copy);
//		}
		return p;
	}
	
	@Override
	public PersonalInfo updatePersonalInfo(Long id, PersonalInfoPostReq personalinfoPostReq) {
		PersonalInfo personalinfo = personalinfoRepository.getOne(id);
		personalinfo.setDateBirth(personalinfoPostReq.getDateBirth());
		personalinfo.setAddress(personalinfoPostReq.getAddress());
		personalinfo.setEnglishName(personalinfoPostReq.getEnglishName());
		personalinfo.setGender(personalinfoPostReq.getGender());
		personalinfo.setRepProfile(personalinfoPostReq.getRepProfile());
		personalinfo.setMilitaryService(personalinfoPostReq.getMilitaryService());
		personalinfo.setVeteransAffairs(personalinfoPostReq.getVeteransAffairs());
		personalinfo.setFinalEducation(personalinfoPostReq.getFinalEducation());
		personalinfo.setDisabled(personalinfoPostReq.getDisabled());
		personalinfo.setTranscript(personalinfoPostReq.getTranscript());
		personalinfo.setIntExtAct(personalinfoPostReq.getIntExtAct());
		return personalinfoRepository.save(personalinfo);
	}
	
	@Override
	public void deletePersonalInfo(Long id) {
		personalinfoRepository.deleteById(id);
	}
	
	@Override
	public long getAllPersonalInfoCount() {
		return personalinfoRepository.count();
	}
	
	@Override
	public List<PersonalInfoPostReq> getAllPersonalInfo(Pageable pageable, String email) {
		String uid = userRepository.findByEmail(email).get().getEmail();
		List<PersonalInfo> list = personalinfoRepository.findAll(pageable).getContent();
		List<PersonalInfoPostReq> copy = new ArrayList<>();
		PersonalInfoPostReq resp;
		for(PersonalInfo p : list) {
			resp = new PersonalInfoPostReq();
			resp.setId(p.getId());
			resp.setEmail(p.getUser().getEmail());
			resp.setName(p.getUser().getName());
			copy.add(resp);
		}
		return copy;
	}

	@Override
	public Certificate createCertificate(Long id, CertificatePostReq certificate) {
		Certificate cer = new Certificate();
		cer.setName(certificate.getName());
		cer.setSortation(certificate.getSortation());
		cer.setAcquisitionDate(certificate.getAcquisitionDate());
		cer.setScore(certificate.getScore());
		PersonalInfo per = personalinfoRepository.getOne(id);
		cer.setPersonalinfo(per);
		return certificateRepository.save(cer);
	}

	@Override
	public Certificate updateCertificate(Long pId, Long cId, CertificatePostReq certificate) {
		Certificate cer = certificateRepository.getOne(cId);
		cer.setName(certificate.getName());
		cer.setSortation(certificate.getSortation());
		cer.setAcquisitionDate(certificate.getAcquisitionDate());
		cer.setScore(certificate.getScore());
		return certificateRepository.save(cer);
	}

	@Override
	public void deleteCertificate(Long pId, Long cId) {
		certificateRepository.deleteById(cId);
	}
	
}
