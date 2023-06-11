package com.linkprice.gmarketcrawler.writer;

import com.linkprice.gmarketcrawler.domain.Gmarket;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class GmarketCsvFlatWriter implements CsvWriter<List<Gmarket>>{

    @Override
    public void writeCSV(List<Gmarket> gmarkets, String filepath) {
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(filepath, true), CSVFormat.DEFAULT)) {

            if (new File(filepath).length() == 0) {
                printer.printRecord("키워드","노출순위","상품명","판매가격","상품이미지URL","구매수","리뷰수","판매자명","스토어URL");
            }

            for(Gmarket gmarket : gmarkets) {
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
