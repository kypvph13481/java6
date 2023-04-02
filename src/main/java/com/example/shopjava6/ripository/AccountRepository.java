package com.example.shopjava6.ripository;

import com.example.shopjava6.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    @Query("SELECT DISTINCT ar.account from Authority ar WHERE ar.role.id IN ('DIRE','STAF')")
    List<Account> getAdmin();
}