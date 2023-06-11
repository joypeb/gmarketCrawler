package com.linkprice.gmarketcrawler.writer;

import com.linkprice.gmarketcrawler.domain.Gmarket;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.util.List;
import java.util.stream.Collectors;

public class GmarketCsvWriter implements CsvWriter<List<List<Gmarket>>>{

    @Override
    public void writeCSV(List<List<Gmarket>> gmarketCrawler, String filepath) {

        List<Gmarket> result = gmarketCrawler.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        try (CSVPrinter printer = new CSVPrinter(new FileWriter(filepath), CSVFormat.DEFAULT)) {

            printer.printRecord("키워드","노출순위","상품명","판매가격","상품이미지URL","구매수","리뷰수","판매자명","스토어URL");

            for(Gmarket gmarket : result) {
                printer.printRecord(
                        gmarket.getKeyword(),gmarket.getRanking(),gmarket.getName()
                        ,gmarket.getPrice(),gmarket.getImageUrl(),gmarket.getPayNum()
                        ,gmarket.getReviewNum(),gmarket.getSellerName(),gmarket.getStoreUrl()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
