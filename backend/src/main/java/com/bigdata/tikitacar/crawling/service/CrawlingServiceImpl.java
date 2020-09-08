package com.bigdata.tikitacar.crawling.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class CrawlingServiceImpl implements CrawlingService {
    @Override
    public void crawlingEncar() {
        String url ="";
        Document document = null;
        try {
            document = Jsoup.connect(url).get();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(document.html());
    }
}
