package com.bigdata.tikitacar.img.service;

import com.bigdata.tikitacar.img.dto.request.ImgRegisterRequestDto;
import com.bigdata.tikitacar.img.dto.request.ImgUploadRequestDto;
import com.bigdata.tikitacar.img.entity.Img;
import com.bigdata.tikitacar.img.repository.ImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ImgServiceImpl implements  ImgService{

    @Autowired
    ImgRepository imgRepository;

    @Override
    public String uploadImg(ImgUploadRequestDto imgUploadRequestDto) {

        String fileName= imgUploadRequestDto.getImg().getOriginalFilename();
        String now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());
        String src = now+"_"+fileName;

        try{
            imgUploadRequestDto.getImg().transferTo(new File(src));
        }catch (Exception e){
            e.printStackTrace();
        }

        return src;
    }

//    @Override
//    public void registerImg(ImgRegisterRequestDto imgRegisterRequestDto) {
//        Img img =Img.builder()
//                .deal(imgRegisterRequestDto.getDeal())
//                .src(imgRegisterRequestDto.getSrc())
//                .build();
//        imgRepository.save(img);
//    }
}
