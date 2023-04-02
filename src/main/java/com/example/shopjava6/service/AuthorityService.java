package com.example.shopjava6.service;

import com.example.shopjava6.entity.Authority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorityService {

    List<Authority> findAuthoritiesOfAdministrator();

    List<Authority> findAll();

    Authority create(Authority auth);

    void delete(Integer id);
}
