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
        String path = "C:\\car\\data.csv";
        ArrayList<int[]> car_info = null;

        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        try {
            bufferedWriter = Files.newBufferedWriter(Paths.get(path), Charset.forName("UTF-8"));
//            initCsv(bufferedWriter);
            car_info = carNumAndSeat();
            int order=1;
            for (int[] car : car_info) {
                int no = car[0];
                int seat = car[1];
                int page = car[2];

//                System.out.println(no);
                System.out.println("page : " + page);
                url = "https://www.bobaedream.co.kr/mycar/mycar_view.php?no=" + no;
                document = Jsoup.connect(url).get();

                //신차가격 준비중인것은 패스, 보험정보 미공개는 패스
                if (document.select("div.info-util.box").first().text().contains("준비중")
                ||document.select("div.info-util.box").first().text().contains("미공개")){
                    continue;
                }

                addToCsv(order, no ,seat ,document, bufferedWriter);
                order++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        element =html.select("div.info-insurance").first();
        st= new StringTokenizer(element.text());

        int flooding=0;
        int insurance = 0;
        while(st.hasMoreTokens()){
            str=st.nextToken();
            if(str.contains("침수")){
                st.nextToken();
                str=st.nextToken();
                if(str.contains("/")||str.equals("")||str.length()==0)
                    flooding+=0;
                else
                    flooding+=Integer.parseInt(str);
            }else if(str.contains("보험사고")){
                str=st.nextToken().replace("회","");
                if(str.contains("/")||str.equals("")||str.length()==0)
                    insurance+=0;
                else
                    insurance+=Integer.parseInt(str);
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

    @Override
    public ArrayList<int[]> carNumAndSeat() throws Exception{
        ArrayList<int[]> ret = new ArrayList<>();

        for(int i=1; i<=100; i++){
            System.out.println(i);
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
                ret.add(new int[] {car_no, seat, i});
            }

            for(Element e : elements_for){
                int index = e.text().indexOf("인승");
                if(index == -1)
                    continue;
                int seat = e.text().charAt(index - 1) - '0';
                int start = e.html().indexOf("php?no=") + 7;
                int end = e.html().indexOf("gubun") - 5;
                int car_no = Integer.parseInt(e.html().substring(start, end));
                ret.add(new int[] {car_no, seat, i});
            }
        }

        return ret;
    }
}
