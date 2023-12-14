package com.example.task3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long matDoc;

    private String item;

    private Date docDate;

    private Date pstngDate;

    private String materialDescription;

    private Integer quantity;

    private String bUn;

    private BigDecimal amountLC;

    private String crcy;

    private String userName;
    private boolean authorizedPosting;

}
