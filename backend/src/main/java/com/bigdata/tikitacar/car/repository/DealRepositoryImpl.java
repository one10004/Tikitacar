package com.bigdata.tikitacar.car.repository;

import com.bigdata.tikitacar.car.dto.request.DealSearchRequestDto;
import com.bigdata.tikitacar.car.dto.response.DealSearchResponseDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DealRepositoryImpl implements DealRepositoryMybatis{

    @Autowired
    SqlSession session;

    @Override
    public List<DealSearchResponseDto> selectDealList(DealSearchRequestDto dealSearchRequestDto) {
        return session.selectList("deal.selectDealList",dealSearchRequestDto);
    }
}
