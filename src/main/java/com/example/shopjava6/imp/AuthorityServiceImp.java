package com.example.shopjava6.imp;

import com.example.shopjava6.entity.Account;
import com.example.shopjava6.entity.Authority;
import com.example.shopjava6.ripository.AccountRepository;
import com.example.shopjava6.ripository.AuthorityRepository;
import com.example.shopjava6.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AuthorityServiceImp implements AuthorityService {
    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    AccountRepository accountRepository;


    @Override
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    @Override
    public List<Authority> findAuthoritiesOfAdmin() {
        List<Account> accounts = accountRepository.getAdmin();
        return authorityRepository.authoritiesOf(accounts);
    }

    @Override
    public Authority create(Authority auth) {
        return authorityRepository.save(auth);
    }

    @Override
    public void delete(Integer id) {
        authorityRepository.deleteById(id);
    }
}
