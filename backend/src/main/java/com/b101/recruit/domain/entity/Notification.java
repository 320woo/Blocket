package com.b101.recruit.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Notification extends BaseEntity {
	
	Long userId; 	// 회원 ID(FK)
	String content; // 내용
}
