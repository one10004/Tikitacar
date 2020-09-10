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
        String path = "C:\\Users\\multicampus\\Desktop\\data.csv";

        try {
            bufferedWriter = Files.newBufferedWriter(Paths.get(path), Charset.forName("UTF-8"));
            initCsv(bufferedWriter);

            int no=1;
            for (int i = 17023; i <= 17023; i++,no++) {
                url = "https://certifiedcar.hyundaicapital.com/hcsfront/ms/carView?xc_vcl_cd=HCL" + i;
                document = Jsoup.connect(url).get();
                addToCsv(no,document, bufferedWriter);
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
    public void addToCsv(int no,Document html, BufferedWriter bufferedWriter) throws Exception {
        Element element = null;
        Elements elements = null;
        String text = null;
        String[] str = new String[2];

        //인덱스
        bufferedWriter.write(no+"");
        bufferedWriter.write(",");

        //차이름
        element = html.select("dl.car_info dt").first();
        text = element.text();
        int idx = text.indexOf("등급");
        text = text.substring(0, idx - 1);
        bufferedWriter.write(text);
        bufferedWriter.write(",");

        //상세이름
        element = html.select("dl.car_info dd").first();
        bufferedWriter.write(element.text());
        bufferedWriter.write(",");

        //가격
        element=html.select("p.sale").first();
        String price = element.text()
                .replace(",","")
                .replace("만원","");
        bufferedWriter.write(price);
        bufferedWriter.write(",");


        // 연식 ~ 압류/저당
        elements = html.select("div.box_gray dl dd");
        int index = 1;
        for(Element e : elements){
            if(index == 7 || index == 8 || index == 10 || index == 12){
                index++;
                continue;
            }
            text = e.text();
            switch (index){ // 색상(4), 인승(6), 압류/저당(11)은 text 따로 처리 x
                case 1: // 연식
                    text = text.substring(0, 4);
                    break;
                case 2: // 주행 거리
                    text = text.replace("Km","");
                    text = text.replace(",","");
                    System.out.println("distance : " + text);
                    break;
                case 3: // 배기량
                    text = text.replace("cc","");
                    text = text.replace(",","");
                    break;
                case 5: // 변속기 / 연료
                    str = text.split("/");
                    break;
                case 9: // 신차 가격
                    text = text.replace("만원", "");
                    text = text.replace(",","");
                    break;
            }
            if(index == 5){
                bufferedWriter.write(str[0]);
                bufferedWriter.write(",");
                bufferedWriter.write(str[1].substring(1));
                bufferedWriter.write(",");
                index++;
                continue;
            }
            index++;
            bufferedWriter.write(text);
            bufferedWriter.write(",");
        }

        //옵션총개수
        elements=html.select("div#option_info span.sum");
        int optSum=0;
        StringTokenizer st = new StringTokenizer(elements.text());
        while(st.hasMoreTokens()){
            optSum+=Integer.parseInt(st.nextToken());
        }
        bufferedWriter.write(optSum+"");
        bufferedWriter.write(",");

        //보험
        element=html.select("li.accident_info strong").first();
        bufferedWriter.write(element.text());
        bufferedWriter.write(",");

        //관리이력
        element=html.select("li.management_info strong").first();
        bufferedWriter.write(element.text());
        bufferedWriter.write(",");

        //침수이력
        element=html.select("li.flooding_info strong").first();
        bufferedWriter.write(element.text());
        bufferedWriter.newLine();
    }
}
