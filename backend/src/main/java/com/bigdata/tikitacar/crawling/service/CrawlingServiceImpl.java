package com.bigdata.tikitacar.crawling.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

@Service
public class CrawlingServiceImpl implements CrawlingService {
    @Override
    public void crawlingCarHtml() {
        String url = null;
        Document document = null;
        BufferedWriter bufferedWriter = null;
        String path = "C:\\data.csv";
        ArrayList<int[]> car_info = null;

        try {
            car_info = carNumAndSeat();

//            bufferedWriter = Files.newBufferedWriter(Paths.get(path), Charset.forName("UTF-8"));
//            initCsv(bufferedWriter);
//
//            int order=1;
//            for (int no = 2074130; no <= 2074130; no++) {
//                url = "https://www.bobaedream.co.kr/mycar/mycar_view.php?no=" + no;
//                document = Jsoup.connect(url).get();
//                if (document.select("div.info-util.box").first().text().contains("준비중") == false) {
//                    addToCsv(order, no ,0 ,document, bufferedWriter);
//                    order++;
//                }
//            }
//            bufferedWriter.close();

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
    public void addToCsv(int order,int no,int seat,Document html, BufferedWriter bufferedWriter) throws Exception {
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

    @Override
    public ArrayList<int[]> carNumAndSeat() throws Exception{
        ArrayList<int[]> ret = new ArrayList<>();

        for(int i=8; i<=8; i++){
            String url_ko = "https://www.bobaedream.co.kr/mycar/mycar_list.php?gubun=K&page=" + Integer.toString(i) + "&order=S11&view_size=20";
            String url_for = "https://www.bobaedream.co.kr/mycar/mycar_list.php?gubun=I&page=" + Integer.toString(i) + "&order=S11&view_size=20";
            Document document_ko = Jsoup.connect(url_ko).get();
            Document document_for = Jsoup.connect(url_for).get();
            Elements elements_ko = document_ko.select("li.product-item div.list-inner div.mode-cell.title dl.data.is-list");
            Elements elements_for = document_for.select("li.product-item div.list-inner div.mode-cell.title dl.data.is-list");

            for(Element e : elements_ko){
                int index_start = e.text().indexOf(" ");
                int index = e.text().indexOf("인승");
                if(index == -1)
                    continue;
                int seat = Integer.parseInt(e.text().substring(index_start + 1, index));
                int start = e.html().indexOf("php?no=") + 7;
                int end = e.html().indexOf("gubun") - 5;
                int car_no = Integer.parseInt(e.html().substring(start, end));
                ret.add(new int[] {car_no, seat});
            }

            for(Element e : elements_for){
                int index = e.text().indexOf("인승");
                if(index == -1)
                    continue;
                int seat = e.text().charAt(index - 1) - '0';
                int start = e.html().indexOf("php?no=") + 7;
                int end = e.html().indexOf("gubun") - 5;
                int car_no = Integer.parseInt(e.html().substring(start, end));
                ret.add(new int[] {car_no, seat});
            }
        }

        return ret;
    }
}
