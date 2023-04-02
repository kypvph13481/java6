package com.example.shopjava6.service;


import com.example.shopjava6.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RolesService {
    List<Role> findAll();
}
