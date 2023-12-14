package com.example.task3.service;

import com.example.task3.entities.AppAccount;
import com.example.task3.entities.PostingItem;
import com.example.task3.repositories.AppAccountRepository;
import com.example.task3.repositories.PostingItemRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostingItemService {
    private final PostingItemRepository repository;
    private final AppAccountRepository accountRepository;

    public void save(PostingItem postingItem) {
        AppAccount appAccount = accountRepository.findByAppAccountName(postingItem.getUserName());
        if (appAccount != null) {
            postingItem.setAuthorizedPosting(appAccount.isActive());
        } else {
            postingItem.setAuthorizedPosting(false);
        }
        repository.save(postingItem);
    }

    public List<PostingItem> findAll() {
        return repository.findAll();
    }

    public List<PostingItem> findAll(String startDate, String endDate, Boolean authorizedPosting) {
        if (StringUtils.isBlank(startDate)) {
            startDate = "01.01.1970";
        }
        if (StringUtils.isBlank(endDate)) {
            LocalDate endLocalDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            endDate = endLocalDate.format(formatter);
        }
        if (authorizedPosting != null) {
            if (authorizedPosting) {
                return repository.findAll(
                                parseDate(startDate), parseDate(endDate)
                        )
                        .stream()
                        .filter(PostingItem::isAuthorizedPosting)
                        .collect(Collectors.toList());
            } else {
                return repository.findAll(
                                parseDate(startDate), parseDate(endDate)
                        )
                        .stream()
                        .filter(postingItem -> !postingItem.isAuthorizedPosting())
                        .collect(Collectors.toList());
            }
        } else {
            return repository.findAll(
                    parseDate(startDate), parseDate(endDate)
            );
        }
    }

    private Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
