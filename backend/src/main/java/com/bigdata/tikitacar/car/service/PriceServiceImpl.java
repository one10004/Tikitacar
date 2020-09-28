package com.bigdata.tikitacar.car.service;

import com.bigdata.tikitacar.car.dto.response.ModelPriceResponseDto;
import com.bigdata.tikitacar.car.entity.CarPrice;
import com.bigdata.tikitacar.car.repository.CarPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService{

    @Autowired
    CarPriceRepository carPriceRepository;

    @Override
    public List<String> readCompanyList() {
        return carPriceRepository.findDistinctByCompany();
    }

    @Override
    public List<String> readModelList(String company) {
        List<CarPrice> carPrices = carPriceRepository.findByCompany(company);
        List<String> modelList = new ArrayList<>();

        for(CarPrice carPrice : carPrices){
            modelList.add(carPrice.getModel());
        }

        return modelList;
    }

    @Override
    public ModelPriceResponseDto readModelPrice(String model) {
        CarPrice carPrice = Optional.of(carPriceRepository.findByModel(model)
                .orElseThrow(() -> new NoSuchElementException("(모델 가격 찾는 중)모델에 해당하는 가격 정보 존재하지 않음."))).get();

        ModelPriceResponseDto modelPriceResponseDto = null;

        modelPriceResponseDto = modelPriceResponseDto.builder()
                .priceLow(carPrice.getPriceLow())
                .priceHigh(carPrice.getPriceHigh())
                .build();

        return modelPriceResponseDto;
    }
}
