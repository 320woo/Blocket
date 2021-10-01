package com.b101.recruit.domain.repository;

import com.b101.recruit.domain.entity.QUserWallet;
import com.b101.recruit.domain.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class JpaUserWalletRepository {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QUserWallet qUserWallet = QUserWallet.userWallet;

    @Transactional
    public long readUserWallet() {
        return 0;
    }

    @Transactional
    public long updateUserWallet() {
        return 0;
    }

    @Transactional
    public long deleteUserWallet(User user) {
        return jpaQueryFactory.delete(qUserWallet).where(qUserWallet.user.id.eq(user.getId())).execute();
    }
}
