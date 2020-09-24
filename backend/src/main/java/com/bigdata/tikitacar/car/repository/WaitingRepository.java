package com.bigdata.tikitacar.car.repository;

import com.bigdata.tikitacar.car.entity.Waiting;
import com.bigdata.tikitacar.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WaitingRepository  extends JpaRepository<Waiting,Long> {
    public List<User> findByDeal_Id(Long dealId);
    public Optional<Waiting> findByDeal_IdAndBuyer_Id(Long dealId, Long buyerId);
}
