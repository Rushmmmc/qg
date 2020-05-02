package com.zhangmengcong.www.po;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/1 11:53
 */
public class DividePageParameter {
    private String currentPage;
    private String rows;
    private String rangeMin;
    private String rangeMax;
    private String rank;
    private Goods  goods;

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getRangeMin() {
        return rangeMin;
    }

    public void setRangeMin(String rangeMin) {
        this.rangeMin = rangeMin;
    }

    public String getRangeMax() {
        return rangeMax;
    }

    public void setRangeMax(String rangeMax) {
        this.rangeMax = rangeMax;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "DividePageParameter{" +
                "currentPage='" + currentPage + '\'' +
                ", rows='" + rows + '\'' +
                ", rangeMin='" + rangeMin + '\'' +
                ", rangeMax='" + rangeMax + '\'' +
                ", rank='" + rank + '\'' +
                ", goods=" + goods +
                '}';
    }
}
