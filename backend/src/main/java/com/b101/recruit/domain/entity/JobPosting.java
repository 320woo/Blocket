package com.b101.recruit.domain.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 채용공고 모델 정의.
 */

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class JobPosting extends BaseEntity {
	
//	private CompanyInfo companyinfo; // 기업정보 id
	private String title; // 채용공고 제목
	private String recruitmentPart; // 모집부분
	private String employmentForm; // 고용형태
	private String introduction; // 소개
	private int salary; // 연봉
	private String occupiedLocations; // 근무지역
	private String requiredAcademicBackground; // 필요학력
	private Enum<TechstackEnum> technologyStack; // 기술스택
	private Date registrationDate; // 등록일
	private Date closingDate; // 마감일

	@PrePersist
	public void createdAt() {
		this.registrationDate = new Date();
	}
	
	@OneToMany
    private List<UserRecruit> users; // 지원자들
	
}
