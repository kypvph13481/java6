package com.example.shopjava6.imp;

import com.example.shopjava6.entity.Account;
import com.example.shopjava6.ripository.AccountRepository;
import com.example.shopjava6.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountServiceImp implements AccountService {
    @Autowired
    AccountRepository accountRepository;


    @Override
    public Account findById(String username) {

        return accountRepository.findById(username).get();
    }

    @Override
    public List<Account> getAdministrator() {
        return accountRepository.getAdmin();
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }


}