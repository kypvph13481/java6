package com.example.shopjava6.service;

import com.example.shopjava6.entity.Authority;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
@ComponentScan
@Service
public interface AuthorityService {

    List<Authority> findAuthoritiesOfAdmin();

    List<Authority> findAll();

    public Authority create(Authority auth);

    void delete(Integer id);
}
