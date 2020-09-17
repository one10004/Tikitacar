package com.bigdata.tikitacar.car.repository;

import com.bigdata.tikitacar.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Long> {
}
