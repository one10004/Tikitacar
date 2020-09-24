package com.bigdata.tikitacar.qna.repository;

import com.bigdata.tikitacar.qna.entity.Qna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QnaRepository extends JpaRepository<Qna, Long> {

    @Query(
            value = "select * from qna where qna.deal = ? order by reg_time DESC",
            countQuery = "select COUNT(*) from qna",
            nativeQuery = true
    )
    Page<Qna> findQnaByDealNumber(Long id, Pageable pageable);
}
