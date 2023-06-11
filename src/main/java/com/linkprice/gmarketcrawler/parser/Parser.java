package com.linkprice.gmarketcrawler.parser;

import java.io.IOException;

public interface Parser<T> {
    T parse(String str) throws IOException;
}
