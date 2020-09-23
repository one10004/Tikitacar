package com.bigdata.tikitacar.img.service;

import com.bigdata.tikitacar.img.dto.request.ImgRegisterRequestDto;
import com.bigdata.tikitacar.img.dto.request.ImgUploadRequestDto;

import java.util.List;

public interface ImgService {
    public List<String> uploadImg(ImgUploadRequestDto imgUploadRequestDto);
//    public void registerImg(ImgRegisterRequestDto imgRegisterRequestDto);
}
