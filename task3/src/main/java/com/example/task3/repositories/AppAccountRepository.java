package com.example.task3.repositories;

import com.example.task3.entities.AppAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppAccountRepository extends JpaRepository<AppAccount, Long>{
    AppAccount findByAppAccountName(String appAccountName);
}
