package com.bigdata.tikitacar.exception;

import lombok.RequiredArgsConstructor;
import net.bis5.mattermost.client4.hook.IncomingWebhookClient;
import net.bis5.mattermost.model.IncomingWebhookRequest;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(HttpServletRequest request
            , HttpServletResponse response
            , Object handler
            , Exception exception) {

        // You can also use builder for create client instance.
        IncomingWebhookClient client = new IncomingWebhookClient("https://meeting.ssafy.com/hooks/74dc7qsk1787zjzkyxmcnfgqne");

        IncomingWebhookRequest payload = new IncomingWebhookRequest();
        payload.setText("Exception 발생 :" + exception.getMessage());

        client.postByIncomingWebhook(payload);

        return exception.getMessage();
    }



}
