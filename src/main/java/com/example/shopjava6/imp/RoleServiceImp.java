package com.example.shopjava6.imp;


import com.example.shopjava6.entity.Role;
import com.example.shopjava6.ripository.RoleRepository;
import com.example.shopjava6.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImp implements RolesService {
   @Autowired
    RoleRepository roleRepository;


    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
