package com.b101.recruit.domain.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chat extends BaseEntity {
	
	private Long userId; 	// 회원 ID(FK)
	private String content; // 내용
	private Date regDate;	// 작성일
	
}
