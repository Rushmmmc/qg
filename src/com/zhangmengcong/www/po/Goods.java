package com.zhangmengcong.www.po;

/**
 * @author:zmc
 * @function: 商品实体类
 * @date: 2020/4/29 13:36
 */
public class Goods {
    private int id;
    private String goodsName;
    private String type;
    private String seller;
    private String imformation;
    private int price;
    private int amount;
    private int boughtAmount;
    private int sellerReputation;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getImformation() {
        return imformation;
    }

    public void setImformation(String imformation) {
        this.imformation = imformation;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBoughtAmount() {
        return boughtAmount;
    }

    public void setBoughtAmount(int boughtAmount) {
        this.boughtAmount = boughtAmount;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getSellerReputation() {
        return sellerReputation;
    }

    public void setSellerReputation(int sellerReputation) {
        this.sellerReputation = sellerReputation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", type='" + type + '\'' +
                ", seller='" + seller + '\'' +
                ", imformation='" + imformation + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", boughtAmount=" + boughtAmount +
                ", sellerReputation=" + sellerReputation +
                ", status='" + status + '\'' +
                '}';
    }
}
