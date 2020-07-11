package com.trelloclone.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trelloclone.demo.models.Account;

public interface AccountRepository extends JpaRepository<Account, String>{

}
