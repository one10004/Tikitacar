package com.bigdata.tikitacar.util;

import org.jsoup.nodes.Document;

import java.io.BufferedWriter;
import java.util.ArrayList;

public interface CrawlingService {

    public void crawlingCarHtml();
    public void initCsv(BufferedWriter bufferedWriter) throws Exception;
    public void addToCsv(int order,int no,int seat, Document html, BufferedWriter bufferedWriter) throws Exception;
    public ArrayList<int[]> carNumAndSeat() throws Exception;
}
