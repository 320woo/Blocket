package com.b101.recruit.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 파일 모델 정의.
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class File extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "personal_info_id", nullable = false)
    private PersonalInfo personalInfo;

    @ManyToOne
    @JoinColumn(name = "certificate_id")
    private Certificate certificate;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    private String filepath;

    private String originName;

    private String saveName;

    private String sortation; // 구분

}