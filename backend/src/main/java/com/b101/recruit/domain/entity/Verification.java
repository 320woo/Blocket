package com.b101.recruit.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;

import lombok.Getter;
import lombok.Setter;

/**
 * 검증 모델 정의.
 */

@Entity
@Getter
@Setter
public class Verification extends BaseEntity {
	
	private PersonalInfo personalinfo; // 신상정보 id
	private Date add_time; // 등록일
	private String currentstatus; // 현재상태(대기중, 승인완료, 거절)
	
	@Column(columnDefinition = "LONGTEXT")
	private String reasonsrejection; // 반려사유
	
	@PrePersist
	public void createdAt() {
		this.add_time = new Date();
	}
}