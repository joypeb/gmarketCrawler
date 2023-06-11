package com.linkprice.gmarketcrawler.crawler;

import org.jsoup.nodes.Document;

public interface Crawler<T> {
    T crawlerDoc(String str);
}
