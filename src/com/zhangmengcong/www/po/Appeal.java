package com.zhangmengcong.www.po;

import java.sql.Timestamp;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/3 19:03
 */
public class Appeal {
    private int id;
    private String type;
    private String username;
    private Timestamp appealDate;
    private String reason;
    private String status;
    private int identId;
    private String seller;
    private String message;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getAppealDate() {
        return appealDate;
    }

    public void setAppealDate(Timestamp appealDate) {
        this.appealDate = appealDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIdentId() {
        return identId;
    }

    public void setIdentId(int identId) {
        this.identId = identId;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "Appeal{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", username='" + username + '\'' +
                ", appealDate=" + appealDate +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                ", identId=" + identId +
                ", seller='" + seller + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
