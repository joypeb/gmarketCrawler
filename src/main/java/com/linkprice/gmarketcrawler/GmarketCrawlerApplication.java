package com.linkprice.gmarketcrawler;

import com.linkprice.gmarketcrawler.crawler.GmarketCrawler;
import com.linkprice.gmarketcrawler.domain.Gmarket;
import com.linkprice.gmarketcrawler.parser.GmarketParser;
import com.linkprice.gmarketcrawler.reader.LineReader;
import com.linkprice.gmarketcrawler.writer.CsvWriter;
import com.linkprice.gmarketcrawler.writer.GmarketCsvWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.List;

@SpringBootApplication
public class GmarketCrawlerApplication {

    public static void main(String[] args) {
        //파일 찾기
        LineReader<Gmarket> lineReader = new LineReader<>(new GmarketParser(new GmarketCrawler()));
        String filename = "./file/keyword.csv";

        //크롤링
        List<List<Gmarket>> gmarketCrawler = lineReader.readLine(filename);

        //csv 파일 생성
        CsvWriter<List<List<Gmarket>>> csvWriter = new GmarketCsvWriter();
        csvWriter.writeCSV(gmarketCrawler, "./file/result.csv");

    }

}
