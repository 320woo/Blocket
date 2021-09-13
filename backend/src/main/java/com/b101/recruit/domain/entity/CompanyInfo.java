package com.b101.recruit.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * 기업정보 모델 정의.
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CompanyInfo extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "use_id")
	private User user; // 해당 기업 소속 사원들
	
    @Column(nullable = false)
    private String name; // 기업명

    private String founded; // 설립일

    private String employees; // 사원수

    private String homePage;
    
    private String email;

    private String phoneNumber;

    private String logo;

    private String address;

    private String industry; // 분야
}
