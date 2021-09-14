package com.b101.recruit.domain.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 회원_좋아요 모델 정의.
 */

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserLike extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user; // 셀프참조
	
	@ManyToOne
	@JoinColumn(name = "company_info_id")
	private CompanyInfo companyinfo;
	
}
