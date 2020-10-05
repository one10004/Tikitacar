package com.bigdata.tikitacar.car.repository;

import com.bigdata.tikitacar.car.entity.Car;
import com.bigdata.tikitacar.car.entity.Deal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal,Long> , DealRepositoryMybatis {

    //리스트 페이징
    Page<Deal> findAllOrderByIdDesc(Pageable pageable);

    List<Deal> findByBuyer_IdOrderByIdDesc(Long id);

}