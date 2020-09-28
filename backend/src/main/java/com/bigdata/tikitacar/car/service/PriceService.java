package com.bigdata.tikitacar.car.service;

import com.bigdata.tikitacar.car.dto.response.ModelPriceResponseDto;

import java.util.List;

public interface PriceService {
    List<String> readCompanyList();
    List<String> readModelList(String company);
    ModelPriceResponseDto readModelPrice(String model);
}
