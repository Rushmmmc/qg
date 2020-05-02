package com.zhangmengcong.www.po;

/**
 * @author:zmc
 * @function: 用于回显用户填写的分页查询信息
 * @date: 2020/5/1 11:37
 */
public class DividePage {
    private String minPrice;
    private String maxPrice;
    private Goods goods;
    private PageBean pb;
    private String rank;

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public PageBean getPb() {
        return pb;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setPb(PageBean pb) {
        this.pb = pb;
    }

    @Override
    public String toString() {
        return "DividePage{" +
                "minPrice='" + minPrice + '\'' +
                ", maxPrice='" + maxPrice + '\'' +
                ", goods=" + goods +
                ", pb=" + pb +
                ", rank='" + rank + '\'' +
                '}';
    }
}
