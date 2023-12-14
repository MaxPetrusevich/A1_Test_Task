package com.example.task3.service;

import com.example.task3.entities.AppAccount;
import com.example.task3.repositories.AppAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppAccountService {
    private final AppAccountRepository appAccountRepository;
    public void save(AppAccount appAccount){
        appAccountRepository.save(appAccount);
    }
}
