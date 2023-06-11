package com.linkprice.gmarketcrawler.parser;

import com.linkprice.gmarketcrawler.crawler.Crawler;
import com.linkprice.gmarketcrawler.domain.Gmarket;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;
import java.util.List;


public class GmarketParser implements Parser {

    Crawler<List<Gmarket>> crawler;

    public GmarketParser(Crawler<List<Gmarket>> crawler) {
        this.crawler = crawler;
    }

    //파싱
    @Override
    public List<Gmarket> parse(String str) throws IOException {
        return crawler.crawlerDoc(str);
    }


}
