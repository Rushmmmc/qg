package com.zhangmengcong.www.po;

import java.sql.Timestamp;

/**
 * @author:zmc
 * @function: 留言消息实体类
 * @date: 2020/5/6 22:11
 */
public class Message {
    private int id;
    private String buyerName;
    private String sellerName;
    private String buyerMessage;
    private String sellerMessage;
    private Timestamp beginDate;
    private int indentId;

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

    public Timestamp getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Timestamp beginDate) {
        this.beginDate = beginDate;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndentId() {
        return indentId;
    }

    public void setIndentId(int indentId) {
        this.indentId = indentId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", buyerName='" + buyerName + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", buyerMessage='" + buyerMessage + '\'' +
                ", sellerMessage='" + sellerMessage + '\'' +
                ", beginDate=" + beginDate +
                ", indentId=" + indentId +
                '}';
    }
}
