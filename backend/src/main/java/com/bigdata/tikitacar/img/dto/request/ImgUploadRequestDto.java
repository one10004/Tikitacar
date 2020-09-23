package com.bigdata.tikitacar.img.dto.request;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImgUploadRequestDto {
    private MultipartFile[] img;
}
