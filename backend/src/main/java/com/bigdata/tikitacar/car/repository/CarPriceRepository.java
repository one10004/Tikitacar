package com.bigdata.tikitacar.car.repository;

import com.bigdata.tikitacar.car.entity.CarPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarPriceRepository extends JpaRepository<CarPrice, Long> {

    @Query(
            value = "select distinct(company) from car_price",
            nativeQuery = true
    )
    List<String> findDistinctByCompany();

    List<CarPrice> findByCompany(String company);
    Optional<CarPrice> findByModel(String model);
}
