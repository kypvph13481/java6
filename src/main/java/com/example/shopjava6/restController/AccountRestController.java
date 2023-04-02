package com.example.shopjava6.restController;


import com.example.shopjava6.entity.Account;
import com.example.shopjava6.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RequestMapping("/rest/accounts")
@RestController
public class AccountRestController {

    @Autowired
    AccountService accountService;

    @GetMapping
    public List<Account> getAccounts(@RequestParam("admin")Optional<Boolean> admin){
        if(admin.orElse(false)){
            return accountService.getAdministrator();
        }return accountService.findAll();
    }


}
