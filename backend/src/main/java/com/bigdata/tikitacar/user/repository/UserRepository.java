package com.bigdata.tikitacar.user.repository;

import com.bigdata.tikitacar.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
