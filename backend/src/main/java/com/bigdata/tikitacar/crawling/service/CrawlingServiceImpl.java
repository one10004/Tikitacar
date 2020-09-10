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
                    addToCsv(no, index ,document, bufferedWriter);
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
        bufferedWriter.write("no");         //인덱스
        bufferedWriter.write(",");
        bufferedWriter.write("name");       //차이름
        bufferedWriter.write(",");
        bufferedWriter.write("subname");    //상세이름
        bufferedWriter.write(",");
        bufferedWriter.write("price");      //가격
        bufferedWriter.write(",");
        bufferedWriter.write("year");       //연식
        bufferedWriter.write(",");
        bufferedWriter.write("distance");   //주행거리
        bufferedWriter.write(",");
        bufferedWriter.write("cc");         //배기량관리
        bufferedWriter.write(",");
        bufferedWriter.write("color");      //색상
        bufferedWriter.write(",");
        bufferedWriter.write("gear");       //변속기
        bufferedWriter.write(",");
        bufferedWriter.write("fuel");       //연료
        bufferedWriter.write(",");
        bufferedWriter.write("seat");       //인승
        bufferedWriter.write(",");
        bufferedWriter.write("release");    //신차가격
        bufferedWriter.write(",");
        bufferedWriter.write("mortgage");   //압류,저당
        bufferedWriter.write(",");
        bufferedWriter.write("option");     //옵션총개수
        bufferedWriter.write(",");
        bufferedWriter.write("insurance");  //보험
        bufferedWriter.write(",");
        bufferedWriter.write("management"); //관리이력
        bufferedWriter.write(",");
        bufferedWriter.write("flooding");      //침수이력
        bufferedWriter.newLine();
    }

    @Override
    public void addToCsv(int no,int index,Document html, BufferedWriter bufferedWriter) throws Exception {
        Element element = null;
        Elements elements = null;
        String text = null;
        StringTokenizer st = null;

        bufferedWriter.newLine();
    }
}
