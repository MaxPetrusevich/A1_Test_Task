package com.example.task3.repositories;

import com.example.task3.entities.PostingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PostingItemRepository extends JpaRepository<PostingItem, Long> {
    @Query("SELECT pi FROM PostingItem pi " +
            "WHERE (pi.docDate BETWEEN :startDate AND :endDate) " +
            "   OR (pi.pstngDate BETWEEN :startDate AND :endDate)")
    List<PostingItem> findAll(@Param("startDate") Date startDate,
                              @Param("endDate") Date endDate);
}
