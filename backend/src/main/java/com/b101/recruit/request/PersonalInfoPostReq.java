package com.b101.recruit.request;

import java.sql.Date;
import java.util.List;

import com.b101.recruit.domain.dto.FileDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("PersonalInfoPostRequest")
public class PersonalInfoPostReq {

	@ApiModelProperty(name = "신상정보 id", example = "Long")
	private Long id;
	@ApiModelProperty(name = "유저 Email", example = "your_email@ssafy.com")
	private String email;
	@ApiModelProperty(name = "유저 이름")
	private String name;
	@ApiModelProperty(name = "생년월일")
	private Date dateBirth;
	@ApiModelProperty(name = "주소")
	private String address; 
	@ApiModelProperty(name = "영문이름")
	private String englishName; 
	@ApiModelProperty(name = "성별")
	private String gender; 		
	@ApiModelProperty(name = "대표프로필")
	private String repProfile; 		
	@ApiModelProperty(name = "병역사항")
	private String militaryService;
	@ApiModelProperty(name = "보훈사항")
	private String veteransAffairs;
	@ApiModelProperty(name = "최종학력")
	private String finalEducation; 
	@ApiModelProperty(name = "장애여부")
	private String disabled; 		
	
	/**/
	@ApiModelProperty(name = "성적증명서")
	private String transcript;      
	@ApiModelProperty(name = "대내외 활동")
	private String intExtAct; 
//	private String prosCons; 		
//	private String selfIntro; 		
//	private String jobExperience; 
	
	@ApiModelProperty(name="파일")
	private List<FileDto> files;
	
}