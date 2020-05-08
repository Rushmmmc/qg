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
    private String goodsType;
    private int price;
    private String seller;
    private String status;
    private int amount;
    private int totalPrice;
    private String reputation;
    private String buyerMessage;
    private String sellerMessage;
    private int useIntegral;
    private int actuallyPrice;
    private String evaluate;
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

    public int getUseIntegral() {
        return useIntegral;
    }

    public void setUseIntegral(int useIntegral) {
        this.useIntegral = useIntegral;
    }

    public int getActuallyPrice() {
        return actuallyPrice;
    }

    public void setActuallyPrice(int actuallyPrice) {
        this.actuallyPrice = actuallyPrice;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    @Override
    public String toString() {
        return "Indent{" +
                "id=" + id +
                ", buyer='" + buyer + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", price=" + price +
                ", seller='" + seller + '\'' +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                ", totalPrice=" + totalPrice +
                ", reputation='" + reputation + '\'' +
                ", buyerMessage='" + buyerMessage + '\'' +
                ", sellerMessage='" + sellerMessage + '\'' +
                ", useIntegral=" + useIntegral +
                ", actuallyPrice=" + actuallyPrice +
                ", evaluate='" + evaluate + '\'' +
                '}';
    }
}
