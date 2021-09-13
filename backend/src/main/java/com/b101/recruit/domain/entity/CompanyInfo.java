package com.b101.recruit.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CompanyInfo extends BaseEntity {

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
