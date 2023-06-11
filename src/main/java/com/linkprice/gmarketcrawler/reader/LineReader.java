package com.linkprice.gmarketcrawler.reader;

import com.linkprice.gmarketcrawler.parser.Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LineReader<T> {

    Parser<List<T>> parser;

    public LineReader(Parser<List<T>> parser) {
        this.parser = parser;
    }

    public List<List<T>> readLine(String filename) {
        List<List<T>> result = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String str;

            //키워드 읽기
            while((str = br.readLine()) != null) {
                result.add(parser.parse(str));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
