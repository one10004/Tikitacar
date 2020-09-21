package com.bigdata.tikitacar.img.repository;

import com.bigdata.tikitacar.img.entity.Img;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImgRepository extends JpaRepository<Img,Long> {
    public void deleteByDeal_Id(Long id);
}
