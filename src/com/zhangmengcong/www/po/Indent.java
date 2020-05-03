package com.zhangmengcong.www.po;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/1 20:13
 */
public class Indent {
    private int id;
    private String buyer;
    private String goodsName;
    private int price;
    private String seller;
    private String status;
    private int amount;
    private int totalPrice;
    private String reputation;
    private String buyerMessage;
    private String sellerMessage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public String getSellerMessage() {
        return sellerMessage;
    }

    public void setSellerMessage(String sellerMessage) {
        this.sellerMessage = sellerMessage;
    }

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }

    @Override
    public String toString() {
        return "Indent{" +
                "id=" + id +
                ", buyer='" + buyer + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", price=" + price +
                ", seller='" + seller + '\'' +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                ", totalPrice=" + totalPrice +
                ", reputation='" + reputation + '\'' +
                ", buyerMessage='" + buyerMessage + '\'' +
                ", sellerMessage='" + sellerMessage + '\'' +
                '}';
    }
}
