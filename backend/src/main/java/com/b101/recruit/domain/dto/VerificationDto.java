package com.b101.recruit.domain.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificationDto {
	private Long id;// 검증 ID
//	private String userEmail;
//	private String userName;
	private Long userId;
	private Long fileId; // 파일 ID
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date registrationDate;
	
	private String currentStatus; // 현재상태(대기중, 승인완료, 거절)
	private String reasonsRejection; // 반려사유
}