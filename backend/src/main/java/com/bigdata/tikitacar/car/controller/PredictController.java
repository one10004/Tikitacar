package com.bigdata.tikitacar.car.controller;

import com.bigdata.tikitacar.car.entity.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/car/predict")
public class PredictController {
//
//    @Autowired
//    PredictService predictService;

    //    @ApiOperation("레시피 상세 조회")
    @PostMapping("")
    public Object getPredictPrice(@RequestBody Car car, HttpServletRequest request) throws Exception {
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("year", String.valueOf(car.getYear()));
        params.add("cc", String.valueOf(car.getCc()));
        params.add("distance", String.valueOf(car.getDistance()));
        params.add("color", car.getColor());
        params.add("gear", car.getGear());
        params.add("fuel", car.getFuel());
        params.add("option", String.valueOf(car.getOption()));
        params.add("seat", String.valueOf(car.getSeat()));
        params.add("flooding", String.valueOf(car.getFlooding()));
        params.add("insurance", String.valueOf(car.getInsurance()));
        params.add("release", String.valueOf(car.getReleasePrice()));

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://127.0.0.1:8000/predict/", params, String.class);

        if(responseEntity!=null) {
            map.put("msg", "중고차 예측 가격 조회를 성공했습니다.");
            map.put("status", "success");
//            map.put("price", responseEntity.);
            System.out.println(responseEntity);
            response = new ResponseEntity(map, HttpStatus.OK);
        }else {
            map.put("msg", "중고차 가격 예측에 실패했습니다.");
            map.put("status", "fail");
            response = new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

}
