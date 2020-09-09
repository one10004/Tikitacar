package com.bigdata.tikitacar.crawling.service;

import org.jsoup.nodes.Document;

import java.io.BufferedWriter;

public interface CrawlingService {

    public void crawlingCarHtml();
    public void initCsv(BufferedWriter bufferedWriter) throws Exception;
    public void addToCsv(int no, Document html, BufferedWriter bufferedWriter) throws Exception;
}
