package com.example.shopjava6.service;

import com.example.shopjava6.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    Account findById(String username);

    List<Account> getAdministrator();

    List<Account> findAll();
}
