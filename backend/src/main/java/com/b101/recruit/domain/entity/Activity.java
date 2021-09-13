package com.b101.recruit.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Activity extends BaseEntity{
	
	
	private Long infoId;		// 신상정보 ID(FK)
	
	private String name;		// 이름
	
	private String activity;	//활동 구분
	
	private String period;		//기간
	
	private String description;	// 설명
	
	boolean verified;			// 검증여부(PASS/FAIL)
	
}
