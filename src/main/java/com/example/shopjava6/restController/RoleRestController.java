package com.example.shopjava6.restController;


import com.example.shopjava6.entity.Role;
import com.example.shopjava6.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/rest/roles")
@RestController
public class RoleRestController {
    @Autowired
    RolesService rolesService;

    @GetMapping
    public List<Role> getAll(){
        return rolesService.findAll();
    }

}
