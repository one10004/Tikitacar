package com.bigdata.tikitacar.exception;

import lombok.RequiredArgsConstructor;
import net.bis5.mattermost.client4.hook.IncomingWebhookClient;
import net.bis5.mattermost.model.IncomingWebhookRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public Object exceptionHandler(HttpServletRequest request
            , HttpServletResponse response
            , Object handler
            , Exception exception) {

        // You can also use builder for create client instance.
//        IncomingWebhookClient client = new IncomingWebhookClient("https://meeting.ssafy.com/hooks/74dc7qsk1787zjzkyxmcnfgqne");
//
//        IncomingWebhookRequest payload = new IncomingWebhookRequest();
//        payload.setText("[ExceptionHandler]\n"+ exception.toString());
//
//        client.postByIncomingWebhook(payload);

        Map<String,Object> map = new HashMap<String, Object>();

        map.put("status","fail");
        map.put("msg", exception.getMessage());
        ResponseEntity res = null;
        res = new ResponseEntity(map, HttpStatus.BAD_REQUEST);

        return res;
    }

}
