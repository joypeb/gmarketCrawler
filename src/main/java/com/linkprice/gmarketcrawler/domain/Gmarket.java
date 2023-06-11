package com.linkprice.gmarketcrawler.domain;

public class Gmarket {
    private String keyword;
    private int ranking;
    private String name;
    private String price;
    private String imageUrl;
    private String payNum;
    private String reviewNum;
    private String sellerName;
    private String storeUrl;

    public Gmarket(String keyword, int ranking, String name, String price, String imageUrl, String payNum, String reviewNum, String sellerName, String storeUrl) {
        this.keyword = keyword;
        this.ranking = ranking;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.payNum = payNum;
        this.reviewNum = reviewNum;
        this.sellerName = sellerName;
        this.storeUrl = storeUrl;
    }

    public String getKeyword() {
        return keyword;
    }

    public int getRanking() {
        return ranking;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPayNum() {
        return payNum;
    }

    public String getReviewNum() {
        return reviewNum;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getStoreUrl() {
        return storeUrl;
    }

    @Override
    public String toString() {
        return "Gmarket{" +
                "keyword='" + keyword + '\'' +
                ", ranking=" + ranking +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", payNum='" + payNum + '\'' +
                ", reviewNum='" + reviewNum + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", storeUrl='" + storeUrl + '\'' +
                '}';
    }
}
