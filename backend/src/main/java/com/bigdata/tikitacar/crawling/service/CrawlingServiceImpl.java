package com.bigdata.tikitacar.crawling.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

            int order=1;
            for (int no = 2075670; no <= 2075670; no++) {
                url = "https://www.bobaedream.co.kr/mycar/mycar_view.php?no=" + no;
                document = Jsoup.connect(url).get();

                //신차가격 준비중인것은 패스, 보험정보 미공개는 패스
                if (document.select("div.info-util.box").first().text().contains("준비중") == false
                || document.select("div.info-util.box").first().text().contains("미공개") ==false) {
                    addToCsv(order, no ,0 ,document, bufferedWriter);
                    order++;
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
        bufferedWriter.write("cc");         bufferedWriter.write(",");      //배기량
        bufferedWriter.write("distance");   bufferedWriter.write(",");      //주행거리
        bufferedWriter.write("color");      bufferedWriter.write(",");      //색상
        bufferedWriter.write("gear");       bufferedWriter.write(",");      //변속기
        bufferedWriter.write("fuel");       bufferedWriter.write(",");      //연료
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
        String str = null;
        StringTokenizer st = null;

        //인덱스
        bufferedWriter.write(order+"");     bufferedWriter.write(",");

        //브랜드-차종
        str = html.select("div.info-price.box.mode-basic h3").first().text();
        bufferedWriter.write(str);   bufferedWriter.write(",");

        //연식, 배기량, 주행거리, 색상, 변속기, 연료
        element =html.select("div.info-basic tbody").first();
        st= new StringTokenizer(element.text());

        while(st.hasMoreTokens()){
            str=st.nextToken();
            if(str.equals("연식")){
                str=st.nextToken();
                bufferedWriter.write(str.substring(0,4)); bufferedWriter.write(",");
            }else if(str.equals("배기량")){
                str=st.nextToken();
                bufferedWriter.write(str.replace(",",""));  bufferedWriter.write(",");
            }else if(str.equals("주행거리")){
                str=st.nextToken();
                if(str.equals("중")==false){
                    bufferedWriter.write(str.replace(",","")); bufferedWriter.write(",");
                }
            }else if(str.equals("색상")){
                str=st.nextToken();
                bufferedWriter.write(str);  bufferedWriter.write(",");
            }else if(str.equals("변속기")){
                str=st.nextToken();
                bufferedWriter.write(str);  bufferedWriter.write(",");
            }else if(str.equals("연료")){
                str=st.nextToken();
                bufferedWriter.write(str);  bufferedWriter.write(",");
            }
        }

        //옵션총개수
        element=html.select("div.tbl-option").first();
        int count = StringUtils.countOccurrencesOf(element.html(),"checked");
        bufferedWriter.write(count+""); bufferedWriter.write(",");

        //인승
        bufferedWriter.write(seat+""); bufferedWriter.write(",");

        //침수이력, 보험횟수
        element =html.select("div.info-insurance tbody").first();
        st= new StringTokenizer(element.text());

        int flooding=0;
        int insurance = 0;
        while(st.hasMoreTokens()){
            str=st.nextToken();
            if(str.contains("침수")){
                st.nextToken();
                str=st.nextToken();
                flooding+=Integer.parseInt(str);
            }else if(str.contains("보험사고")){
                str=st.nextToken();
                insurance+=Integer.parseInt(str.replace("회",""));
            }
        }
        bufferedWriter.write(flooding+""); bufferedWriter.write(",");
        bufferedWriter.write(insurance+""); bufferedWriter.write(",");

        //출고가, 중고가
        String url = "https://www.bobaedream.co.kr/mycar/popup/newCarCompare.php?tb=mycar&no=" + no;
        Document document = Jsoup.connect(url).get();
        element =document.select("div.tbl-simulation tbody").first();
        st= new StringTokenizer(element.text());

        boolean end= false;
        while(st.hasMoreTokens()){
            str=st.nextToken();
            if(str.contains("만원")){
                bufferedWriter.write(str
                        .replace("-","")
                        .replace(",","")
                        .replace("만원",""));
                if(end==false){
                    bufferedWriter.write(",");  end=true;
                }
            }
        }

        bufferedWriter.newLine();
    }
}
