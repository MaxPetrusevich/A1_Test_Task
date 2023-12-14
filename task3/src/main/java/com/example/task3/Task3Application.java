package com.example.task3;

import com.example.task3.service.DataImportService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Task3Application {
    private final DataImportService dataImportService;
    public static void main(String[] args) {
        SpringApplication.run(Task3Application.class, args);
    }
    @PostConstruct
    public void importDataFromCSV() {
        dataImportService.importDataFromCSV("logins.csv", "postings.csv");
    }

}
