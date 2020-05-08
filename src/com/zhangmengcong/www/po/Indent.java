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
    private float price;
    private String seller;
    private String status;
    private int amount;
    private float totalPrice;
    private String reputation;
    private String buyerMessage;
    private String sellerMessage;
    private int useIntegral;
    private float actuallyPrice;
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
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

    public int getUseIntegral() {
        return useIntegral;
    }

    public void setUseIntegral(int useIntegral) {
        this.useIntegral = useIntegral;
    }

    public float getActuallyPrice() {
        return actuallyPrice;
    }

    public void setActuallyPrice(float actuallyPrice) {
        this.actuallyPrice = actuallyPrice;
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
