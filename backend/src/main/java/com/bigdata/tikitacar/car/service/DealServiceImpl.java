package com.bigdata.tikitacar.car.service;

import com.bigdata.tikitacar.car.dto.request.DealRegisterRequestDto;
import com.bigdata.tikitacar.car.dto.request.DealSearchRequestDto;
import com.bigdata.tikitacar.car.dto.request.DealUpdateRequestDto;
import com.bigdata.tikitacar.car.dto.request.DealUpdateStatusRequestDto;
import com.bigdata.tikitacar.car.dto.response.DealDetailResponseDto;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;



@Service
public class DealServiceImpl implements DealService {

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
        User seller = Optional.of(userRepository.findById(dealRegisterRequestDto.getSellerId())
                .orElseThrow(() -> new NoSuchElementException("유저가 존재하지 않음."))).get();

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

        try {
            car= carRepository.save(car);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Deal deal = Deal.builder()
                .seller(seller)
                .car(car)
                .status("판매중")
                .date(LocalDateTime.now())
                .title(dealRegisterRequestDto.getTitle())
                .content(dealRegisterRequestDto.getContent())
                .build();

        try {
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
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Read
    @Override
    public DealDetailResponseDto searchDeal(Long id) {
        Deal deal = Optional.of(dealRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("거래가 존재하지 않음."))).get();

        List<Img> imgList = imgRepository.findByDeal_Id(id);
        List<String> srcList = new ArrayList<>();
        for (Img img : imgList) {
            srcList.add(img.getSrc());
        }

        DealDetailResponseDto dealDetailResponseDto = DealDetailResponseDto.builder()
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
                .status(deal.getStatus())
                .src(srcList)
                .build();

        return dealDetailResponseDto;
    }

    //Update
    @Override
    @Transactional
    public void updateDeal(DealUpdateRequestDto dealUpdateRequestDto) {
        Deal deal = Optional.of(dealRepository.findById(dealUpdateRequestDto.getId())
                .orElseThrow(() -> new NoSuchElementException("거래가 존재하지 않음."))).get();


        try {
            //이전 이미지 다지우기
            imgRepository.deleteByDeal_Id(deal.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        try {
            //이미지 새로추가
            for(String s :dealUpdateRequestDto.getSrc()){
                Img img = Img.builder()
                        .deal(deal)
                        .src(s)
                        .build();
                imgRepository.save(img);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    @Transactional
    public void updateDealStatus(Long dealId, String email) {
        Deal deal = Optional.of(dealRepository.findById(dealId))
                .orElseThrow(() -> new NoSuchElementException("거래가 존재하지 않음.")).get();
        User buyer = Optional.of(userRepository.findByEmail(email))
                .orElseThrow(() -> new NoSuchElementException("유저가 존재하지 않음.")).get();
        try{
            deal.updateDealStatus("판매완료", buyer);
        }catch (Exception e){
            e.printStackTrace();
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
    public List<DealDetailResponseDto> searchAll(Pageable pageable) {
        List<DealDetailResponseDto> dealDetailResponseDtoList = new ArrayList<>();

        Page<Deal> list = dealRepository.findAll(pageable);

        for(Deal deal : list) {
            dealDetailResponseDtoList.add(DealDetailResponseDto.builder()
                    .id(deal.getId())
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
                    .status(deal.getStatus())
                    .build());
        }

        return dealDetailResponseDtoList;
    }

    @Override
    public List<DealSearchResponseDto> searchDetail(DealSearchRequestDto dealSearchRequestDto) {
        List<DealSearchResponseDto> dealSearchResponseDto = dealRepository.selectDealList(dealSearchRequestDto);
        return dealSearchResponseDto;
    }

    @Override
    public List<DealSearchResponseDto> searchMy(Long id) {
        List<Deal> dealList = dealRepository.findByBuyer_Id(id);
        List<DealSearchResponseDto> dealSearchResponseDtoList=new ArrayList<>();


        for (Deal deal : dealList) {
            List<Img> imgList = imgRepository.findByDeal_Id(deal.getId());


            DealSearchResponseDto dealSearchResponseDto = DealSearchResponseDto.builder()
                    .id(deal.getId())
                    .name(deal.getCar().getName())
                    .nickname(deal.getSeller().getNickname())
                    .releasePrice(deal.getCar().getReleasePrice())
                    .price(deal.getCar().getPrice())
                    .title(deal.getTitle())
                    .src(imgList.get(0).getSrc())
                    .status(deal.getStatus())
                    .build();

            dealSearchResponseDtoList.add(dealSearchResponseDto);
        }

        return dealSearchResponseDtoList;
    }
}
