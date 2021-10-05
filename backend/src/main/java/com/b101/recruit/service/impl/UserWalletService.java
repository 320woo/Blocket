package com.b101.recruit.service.impl;

import com.b101.recruit.domain.entity.User;
import com.b101.recruit.domain.entity.UserWallet;
import com.b101.recruit.domain.repository.JpaUserWalletRepository;
import com.b101.recruit.domain.repository.UserRepository;
import com.b101.recruit.domain.repository.UserWalletRepository;
import com.b101.recruit.request.UserWalletRegisterPostReq;
import com.b101.recruit.request.UserWalletUpdatePatchReq;
import com.b101.recruit.service.IUserWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userWalletService")
public class UserWalletService implements IUserWalletService {

    @Autowired
    UserWalletRepository userWalletRepository;

    @Autowired
    JpaUserWalletRepository jpaUserWalletRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Override
    public void createUserWallet(UserWalletRegisterPostReq userWalletRegisterPostReq) {
        User user = userService.findByUserEmail(userWalletRegisterPostReq.getEmail());
        UserWallet userWallet = new UserWallet();
        userWallet.setUser(user);
        userWallet.setAddress(userWalletRegisterPostReq.getAddress());
        userWallet.setBalance(userWalletRegisterPostReq.getBalance());
        userWallet.setReceiving_count(userWalletRegisterPostReq.getReceiving_count());
        userWalletRepository.save(userWallet);
    }

    @Override
    public Optional<UserWallet> findUserWallet(Long userId) {
        return jpaUserWalletRepository.findByUserId(userId);
    }

    @Override
    public long updateUserWallet(Long userId, UserWalletUpdatePatchReq userWalletUpdatePatchReq) {
        return jpaUserWalletRepository.updateUserWallet(userId, userWalletUpdatePatchReq);
    }
}
