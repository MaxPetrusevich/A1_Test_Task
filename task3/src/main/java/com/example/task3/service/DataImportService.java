package com.example.task3.service;

import com.example.task3.entities.AppAccount;
import com.example.task3.entities.PostingItem;
import com.example.task3.repositories.AppAccountRepository;
import com.example.task3.repositories.PostingItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class DataImportService {


    private final AppAccountRepository appAccountRepository;

    private final PostingItemRepository postingItemRepository;


    public void importDataFromCSV(String appAccountCsvPath, String postingItemCsvPath) {
        importAppAccounts(appAccountCsvPath);
        importPostingItems(postingItemCsvPath);
    }

    private void importAppAccounts(String appAccountCsvPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(appAccountCsvPath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                AppAccount appAccount = new AppAccount();
                appAccount.setApplication(fields[0].trim());
                appAccount.setAppAccountName(fields[1].trim());
                appAccount.setActive(Boolean.parseBoolean(fields[2].trim()));
                appAccount.setJobTitle(fields[3].trim());
                appAccount.setDepartment(fields[4].trim());
                appAccountRepository.save(appAccount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void importPostingItems(String postingItemCsvPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(postingItemCsvPath))) {
            String line;
            reader.readLine();
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                PostingItem postingItem = new PostingItem();
                postingItem.setMatDoc(Long.parseLong(fields[0].trim()));
                postingItem.setItem(fields[1].trim());
                postingItem.setDocDate(parseDate(fields[2].trim()));
                postingItem.setPstngDate(parseDate(fields[3].trim()));
                postingItem.setMaterialDescription(fields[4].trim());
                postingItem.setQuantity(Integer.parseInt(fields[5].trim()));
                postingItem.setBUn(fields[6].trim());
                postingItem.setAmountLC(new BigDecimal(fields[7].trim().replace(',', '.')));
                postingItem.setCrcy(fields[8].trim());
                postingItem.setUserName(fields[9].trim());
                AppAccount appAccount = appAccountRepository.findByAppAccountName(postingItem.getUserName());
                if (appAccount != null) {
                    postingItem.setAuthorizedPosting(appAccount.isActive());
                } else {
                    postingItem.setAuthorizedPosting(false);
                }
                postingItemRepository.save(postingItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
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
