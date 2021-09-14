package com.b101.recruit.domain.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.PrePersist;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chat extends BaseEntity {
	
	private Long userId; 	// 회원 ID(FK)
	private String content; // 내용
	private Date regDate;	// 작성일
	
	@PrePersist
	public void createdAt() {
		this.regDate = new Date();
	}
}
