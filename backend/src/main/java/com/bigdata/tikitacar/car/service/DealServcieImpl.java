package com.bigdata.tikitacar.car.service;

import com.bigdata.tikitacar.car.dto.request.DealRegisterRequestDto;
import com.bigdata.tikitacar.car.dto.request.DealUpdateRequestDto;
import com.bigdata.tikitacar.car.dto.response.DealSearchResponseDto;
import com.bigdata.tikitacar.car.entity.Car;
import com.bigdata.tikitacar.car.entity.Deal;
import com.bigdata.tikitacar.car.repository.CarRepository;
import com.bigdata.tikitacar.car.repository.DealRepository;
import com.bigdata.tikitacar.img.entity.Img;
import com.bigdata.tikitacar.img.repository.ImgRepository;
import com.bigdata.tikitacar.user.entity.User;
import com.bigdata.tikitacar.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DealServcieImpl implements DealService {

    @Autowired
    DealRepository dealRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ImgRepository imgRepository;

    //Create
    @Override
    @Transactional
    public void registerDeal(DealRegisterRequestDto dealRegisterRequestDto) {
        User seller= userRepository.findById(dealRegisterRequestDto.getSellerId()).get();

        //차등록
        Car car = Car.builder()
                .name(dealRegisterRequestDto.getName())
                .year(dealRegisterRequestDto.getYear())
                .cc(dealRegisterRequestDto.getCc())
                .distance(dealRegisterRequestDto.getDistance())
                .color(dealRegisterRequestDto.getColor())
                .gear(dealRegisterRequestDto.getGear())
                .fuel(dealRegisterRequestDto.getFuel())
                .seat(dealRegisterRequestDto.getSeat())
                .flooding(dealRegisterRequestDto.getFlooding())
                .insurance(dealRegisterRequestDto.getInsurance())
                .releasePrice(dealRegisterRequestDto.getReleasePrice())
                .price(dealRegisterRequestDto.getPrice())
                .build();

        car= carRepository.save(car);

        Deal deal = Deal.builder()
                .seller(seller)
                .car(car)
                .status("판매중")
                .title(dealRegisterRequestDto.getTitle())
                .content(dealRegisterRequestDto.getContent())
                .build();

        dealRepository.save(deal);

        //이미지 업로드
        for(String s :dealRegisterRequestDto.getSrc()){
            Img img = Img.builder()
                    .deal(deal)
                    .src(s)
                    .build();
            imgRepository.save(img);
        }
    }

    //Read
    @Override
    public DealSearchResponseDto searchDeal(Long id) {
        Deal deal = dealRepository.findById(id).get();
        DealSearchResponseDto dealSearchResponseDto = DealSearchResponseDto.builder()
                .email(deal.getSeller().getEmail())
                .nickname(deal.getSeller().getEmail())
                .phone(deal.getSeller().getPhone())
                .address(deal.getSeller().getAddress())
                .name(deal.getCar().getName())
                .year(deal.getCar().getYear())
                .cc(deal.getCar().getCc())
                .distance(deal.getCar().getDistance())
                .color(deal.getCar().getColor())
                .gear(deal.getCar().getGear())
                .fuel(deal.getCar().getFuel())
                .seat(deal.getCar().getSeat())
                .flooding(deal.getCar().getFlooding())
                .insurance(deal.getCar().getInsurance())
                .releasePrice(deal.getCar().getReleasePrice())
                .price(deal.getCar().getPrice())
                .title(deal.getTitle())
                .content(deal.getContent())
                .build();

        return dealSearchResponseDto;
    }

    //Update
    @Override
    @Transactional
    public void updateDeal(DealUpdateRequestDto dealUpdateRequestDto) {
        Deal deal = dealRepository.findById(dealUpdateRequestDto.getId()).get();

        //이전 이미지 다지우기
        imgRepository.deleteByDeal_Id(deal.getId());

        Car car =deal.getCar();

        car.updateCar(dealUpdateRequestDto.getName(),
                dealUpdateRequestDto.getYear(),
                dealUpdateRequestDto.getCc(),
                dealUpdateRequestDto.getDistance(),
                dealUpdateRequestDto.getColor(),
                dealUpdateRequestDto.getGear(),
                dealUpdateRequestDto.getFuel(),
                dealUpdateRequestDto.getSeat(),
                dealUpdateRequestDto.getFlooding(),
                dealUpdateRequestDto.getInsurance(),
                dealUpdateRequestDto.getReleasePrice(),
                dealUpdateRequestDto.getPrice());

        deal.updateDeal(dealUpdateRequestDto.getTitle(),
                dealUpdateRequestDto.getContent());

        //이미지 새로추가
        for(String s :dealUpdateRequestDto.getSrc()){
            Img img = Img.builder()
                    .deal(deal)
                    .src(s)
                    .build();
            imgRepository.save(img);
        }
        
    }

    //Delete
    @Override
    @Transactional
    public void removeDeal(Long id) {
        dealRepository.deleteById(id);
    }

    //List
    @Override
    public List<DealSearchResponseDto> searchAll(Pageable pageable) {
        List<DealSearchResponseDto> dealSearchResponseDtoList = new ArrayList<>();

        Page<Deal> list = dealRepository.findAll(pageable);

        for(Deal deal : list) {
            dealSearchResponseDtoList.add(DealSearchResponseDto.builder()
                    .email(deal.getSeller().getEmail())
                    .nickname(deal.getSeller().getEmail())
                    .phone(deal.getSeller().getPhone())
                    .address(deal.getSeller().getAddress())
                    .name(deal.getCar().getName())
                    .year(deal.getCar().getYear())
                    .cc(deal.getCar().getCc())
                    .distance(deal.getCar().getDistance())
                    .color(deal.getCar().getColor())
                    .gear(deal.getCar().getGear())
                    .fuel(deal.getCar().getFuel())
                    .seat(deal.getCar().getSeat())
                    .flooding(deal.getCar().getFlooding())
                    .insurance(deal.getCar().getInsurance())
                    .releasePrice(deal.getCar().getReleasePrice())
                    .price(deal.getCar().getPrice())
                    .title(deal.getTitle())
                    .content(deal.getContent())
                    .build());
        }

        return dealSearchResponseDtoList;
    }
}
