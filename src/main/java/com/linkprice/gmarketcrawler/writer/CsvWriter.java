package com.linkprice.gmarketcrawler.writer;

public interface CsvWriter<T> {

    void writeCSV(T t, String filepath);
}
