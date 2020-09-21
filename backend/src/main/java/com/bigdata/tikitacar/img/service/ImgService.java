package com.bigdata.tikitacar.img.service;

import com.bigdata.tikitacar.img.dto.request.ImgRegisterRequestDto;
import com.bigdata.tikitacar.img.dto.request.ImgUploadRequestDto;

public interface ImgService {
    public String uploadImg(ImgUploadRequestDto imgUploadRequestDto);
//    public void registerImg(ImgRegisterRequestDto imgRegisterRequestDto);
}
