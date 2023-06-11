package com.linkprice.gmarketcrawler.crawler;

import com.linkprice.gmarketcrawler.domain.Gmarket;
import com.linkprice.gmarketcrawler.writer.CsvWriter;
import com.linkprice.gmarketcrawler.writer.GmarketCsvFlatWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GmarketCrawler implements Crawler{

    //Document 생성 및 Gmarket객체 리스트 리턴
    @Override
    public List<Gmarket> crawlerDoc(String str) {
        //url 설정
        String url = "https://browse.gmarket.co.kr/search?keyword=";
        List<Gmarket> gmarkets = null;

        CsvWriter<List<Gmarket>> csvWriter = new GmarketCsvFlatWriter();

        try {
            //jsoup을 이용해 Document가져오기
            Document doc = Jsoup.connect(url + str).get();

            //분석
            gmarkets = crawling(doc, str);

            //csv저장
            csvWriter.writeCSV(gmarkets, "./file/output.csv");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return gmarkets;
    }

    //Document를 이용해 Elements에 있는 요소들을 분석하여 정보 추출 메서드
    public List<Gmarket> crawling(Document doc, String str) {
        List<Gmarket> gmarkets = new ArrayList<>();

        List<Element> elements = doc.select(".box__information").subList(5,13);

        System.out.println(str);

        elements.parallelStream().forEach(element -> {
            int ranking = elements.indexOf(element) + 1;

            String name = "";
            Elements nameElements = element.getElementsByClass("text__item");
            if(!nameElements.isEmpty())
                name = nameElements.first().text();

            String price = "";
            Elements priceElements = element.getElementsByClass("text text__value");
            if(!priceElements.isEmpty())
                price = priceElements.first().text();

            String imgUrl = "";
            Elements imgUrlElementsSibling = element.previousElementSiblings();
            Elements imgUrlElements = null;
            if(!imgUrlElementsSibling.isEmpty()) {
                imgUrlElements = imgUrlElementsSibling.first().getElementsByTag("img");
            }
            if(!imgUrlElements.isEmpty())
                imgUrl = imgUrlElements.first().attr("src");
            if(!imgUrl.startsWith("https:")) imgUrl = "https:" + imgUrl;

            String payNum = "";
            Elements payCountElements = element.getElementsByClass("list-item list-item__pay-count");
            if(!payCountElements.isEmpty()) {
                String[] payCountArr = payCountElements.first().child(0).text().split(" ");
                payNum = payCountArr[1];
            }

            String reviewCount = "";
            Elements reviewCountElements = element.getElementsByClass("list-item list-item__feedback-count");
            if(!reviewCountElements.isEmpty())
                reviewCount = reviewCountElements.first().child(1).text().replace("(","").replace(")","");


            String sellerName = "";
            Elements sellerNameElements = element.getElementsByClass("text__seller");
            if(!sellerNameElements.isEmpty())
                sellerName = element.getElementsByClass("text__seller").text();

            String storeUrl = "";
            Elements storeUrlElements = element.getElementsByClass("link__shop");
            if(!storeUrlElements.isEmpty())
                storeUrl = element.getElementsByClass("link__shop").attr("href");

            synchronized (gmarkets) {
                gmarkets.add(new Gmarket(str, ranking, name, price, imgUrl, payNum, reviewCount, sellerName, storeUrl));
            }
        });

        return gmarkets;
    }
}
