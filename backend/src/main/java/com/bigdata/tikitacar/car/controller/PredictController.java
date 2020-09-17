package com.bigdata.tikitacar.car.controller;

import com.bigdata.tikitacar.car.dto.request.RequestCarPredict;
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
    public Object getPredictPrice(@RequestBody RequestCarPredict requestCarPredict, HttpServletRequest request) throws Exception {
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("year", String.valueOf(requestCarPredict.getYear()));
        params.add("cc", String.valueOf(requestCarPredict.getCc()));
        params.add("distance", String.valueOf(requestCarPredict.getDistance()));
        params.add("color", requestCarPredict.getColor());
        params.add("gear", requestCarPredict.getGear());
        params.add("fuel", requestCarPredict.getFuel());
        params.add("option", String.valueOf(requestCarPredict.getOption()));
        params.add("seat", String.valueOf(requestCarPredict.getSeat()));
        params.add("flooding", String.valueOf(requestCarPredict.getFlooding()));
        params.add("insurance", String.valueOf(requestCarPredict.getInsurance()));
        params.add("release", String.valueOf(requestCarPredict.getReleasePrice()));

        ResponseEntity<Map> responseEntity = restTemplate.postForEntity("http://127.0.0.1:8000/predict/", params, Map.class);

        if(responseEntity!=null) {
            map.put("msg", "중고차 예측 가격 조회를 성공했습니다.");
            map.put("status", "success");
            map.put("car_name", requestCarPredict.getName());
            int price = (int) Math.round((double) responseEntity.getBody().get("result"));
            if(price > requestCarPredict.getReleasePrice()*0.9) price =  (int) Math.round(requestCarPredict.getReleasePrice() * 0.9);
            else if(price < 150) price = 150;
            map.put("price", price);
            response = new ResponseEntity(map, HttpStatus.OK);
        }else {
            map.put("msg", "중고차 가격 예측에 실패했습니다.");
            map.put("status", "fail");
            response = new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

}
