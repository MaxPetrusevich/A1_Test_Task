package com.example.task3.controller;

import com.example.task3.entities.PostingItem;
import com.example.task3.service.PostingItemService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/postings")
@RequiredArgsConstructor
public class PostingController {
    private final PostingItemService service;
    @GetMapping
    public ResponseEntity<List<PostingItem>> getPostingItems(@RequestParam(value = "startDate", required = false)  String startDate,
                                                             @RequestParam(value = "endDate", required = false)  String endDate,
                                                             @RequestParam(value = "authorizedPosting", required = false) Boolean authorizedPosting){
        return new ResponseEntity<>(service.findAll(startDate, endDate, authorizedPosting),HttpStatus.OK);
    }
}
