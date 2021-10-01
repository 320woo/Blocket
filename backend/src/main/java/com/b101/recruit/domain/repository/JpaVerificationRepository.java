package com.b101.recruit.domain.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.b101.recruit.domain.dto.VerificationDto;
import com.b101.recruit.domain.entity.QFile;
import com.b101.recruit.domain.entity.QVerification;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class JpaVerificationRepository {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	QVerification qVerification = QVerification.verification;
	QFile qFile = QFile.file;

	@Transactional
	public Page<VerificationDto> findAll(Pageable pageable) {
//		List<VerificationDto> verificationDto = jpaQueryFactory
//				.select(Projections.constructor(VerificationDto.class, qVerification.id, qFile));
//		if(pageable.getPageNumber()==0) {
//			long cnt = jpaQueryFactory.selectFrom(qVerification).fetchCount();
//		}
		return null;
	}
}