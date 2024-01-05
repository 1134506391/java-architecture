package com.study.pojo.vo;

/**
 * 用于展示商品搜索列表结果的VO
 */
public class SearchItemsVO {

    // 商品ID
    private String itemId;
    // 商品名称
    private String itemName;
    // 销量
    private int sellCounts;
    // 图片路径
    private String imgUrl;
    // 售价[金额都以分为单位，避免计算金额时的精度问题，在前端中金额需要 /100]
    private int price;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getSellCounts() {
        return sellCounts;
    }

    public void setSellCounts(int sellCounts) {
        this.sellCounts = sellCounts;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
