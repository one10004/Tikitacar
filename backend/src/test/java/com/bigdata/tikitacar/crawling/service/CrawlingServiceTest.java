package com.bigdata.tikitacar.crawling.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.net.ssl.SSLHandshakeException;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class CrawlingServiceTest {
    @Autowired
    CrawlingService crawlingService;

    @DisplayName("false 로 실행")
    @RepeatedTest(10)
    public void crawl(){

        System.setProperty("jsse.enableSNIExtension", "false");
        assertDoesNotThrow(()->{
            crawlingService.crawlingCarHtml();

        });
    }

    @DisplayName("true 로 실행")
    @RepeatedTest(10)
    public void crawl2(){
        System.setProperty("jsse.enableSNIExtension", "true");

        assertDoesNotThrow(()->{
            crawlingService.crawlingCarHtml();

        });
    }
}