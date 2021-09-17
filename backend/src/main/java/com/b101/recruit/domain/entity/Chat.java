package com.b101.recruit.domain.entity;

import java.util.Date; 

import javax.persistence.Entity;
import javax.persistence.PrePersist;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 채팅 모델 정의.
 */

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Chat extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user; 	// 회원 ID(FK)
	
	private String content; // 내용
	private Date regDate;	// 작성일
	
	@PrePersist
	public void createdAt() {
		this.regDate = new Date();
	}
}
