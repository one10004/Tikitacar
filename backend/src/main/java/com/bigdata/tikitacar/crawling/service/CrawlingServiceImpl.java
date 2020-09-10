package com.bigdata.tikitacar.crawling.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

@Service
public class CrawlingServiceImpl implements CrawlingService {
    @Override
    public void crawlingCarHtml() {
        String url = null;
        Document document = null;
        BufferedWriter bufferedWriter = null;
        String path = "C:\\data.csv";

        try {
            bufferedWriter = Files.newBufferedWriter(Paths.get(path), Charset.forName("UTF-8"));
            initCsv(bufferedWriter);

            int no=1;
            for (int index = 2074130; index <= 2074130; index++) {
                url = "https://www.bobaedream.co.kr/mycar/mycar_view.php?no=" + index;
                document = Jsoup.connect(url).get();
                if (document.select("div.info-util.box").first().text().contains("준비중") == false) {
                    addToCsv(no, index ,0 ,document, bufferedWriter);
                    no++;
                }
            }
            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initCsv(BufferedWriter bufferedWriter) throws Exception {
        bufferedWriter.write("no");         bufferedWriter.write(",");      //인덱스
        bufferedWriter.write("name");       bufferedWriter.write(",");      //브랜드,차종
        bufferedWriter.write("year");       bufferedWriter.write(",");      //연식
        bufferedWriter.write("distance");   bufferedWriter.write(",");      //주행거리
        bufferedWriter.write("gear");       bufferedWriter.write(",");      //변속기
        bufferedWriter.write("fuel");       bufferedWriter.write(",");      //연료
        bufferedWriter.write("cc");         bufferedWriter.write(",");      //배기량
        bufferedWriter.write("color");      bufferedWriter.write(",");      //색상
        bufferedWriter.write("option");     bufferedWriter.write(",");      //옵션총개수
        bufferedWriter.write("seat");       bufferedWriter.write(",");      //인승
        bufferedWriter.write("flooding");   bufferedWriter.write(",");      //침수이력
        bufferedWriter.write("insurance");  bufferedWriter.write(",");      //보험횟수
        bufferedWriter.write("release");    bufferedWriter.write(",");      //출고가
        bufferedWriter.write("price");                                          //중고가
        bufferedWriter.newLine();
    }

    @Override
    public void addToCsv(int no,int index,int seat,Document html, BufferedWriter bufferedWriter) throws Exception {
        Element element = null;
        Elements elements = null;
        String text = null;
        StringTokenizer st = null;



        //인덱스
        //브랜드,차종
        //연식
        //주행거리
        //변속기
        //연료
        //배기량
        //색상
        //옵션총개수
        //인승
        //침수이력
        //보험횟수
        //출고가
        //중고가



        bufferedWriter.newLine();
    }
}
