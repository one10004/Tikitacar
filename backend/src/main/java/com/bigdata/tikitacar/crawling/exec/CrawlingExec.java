package com.bigdata.tikitacar.crawling.exec;

import com.bigdata.tikitacar.crawling.service.CrawlingService;
import com.bigdata.tikitacar.crawling.service.CrawlingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class CrawlingExec {
    public static void main(String[] args) {
        CrawlingService crawlingService = new CrawlingServiceImpl();
        crawlingService.crawlingEncar();
    }
}
