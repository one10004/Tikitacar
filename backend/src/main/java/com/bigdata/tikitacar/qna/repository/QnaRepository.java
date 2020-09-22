package com.bigdata.tikitacar.qna.repository;

import com.bigdata.tikitacar.qna.entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<Qna, Long> {
}
