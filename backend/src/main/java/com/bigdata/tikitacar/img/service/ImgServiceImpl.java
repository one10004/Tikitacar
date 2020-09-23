package com.bigdata.tikitacar.img.service;

import com.bigdata.tikitacar.img.dto.request.ImgRegisterRequestDto;
import com.bigdata.tikitacar.img.dto.request.ImgUploadRequestDto;
import com.bigdata.tikitacar.img.entity.Img;
import com.bigdata.tikitacar.img.repository.ImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImgServiceImpl implements  ImgService{

    @Autowired
    ImgRepository imgRepository;

    @Override
    public List<String> uploadImg(ImgUploadRequestDto imgUploadRequestDto) {

        List<String> result=new ArrayList<>();
        int len=imgUploadRequestDto.getImg().length;

        for(int i=0;i<len;i++){

            String fileName= imgUploadRequestDto.getImg()[i].getOriginalFilename();
            String now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());
            String src = now+"_"+fileName;

            try{
                imgUploadRequestDto.getImg()[i].transferTo(new File(src));
                result.add(src);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return result;
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
