package com.zhangmengcong.www.po;

import java.sql.Timestamp;

/**
 * @author:zmc function:
 * date:2020/4/29 20:59
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String mailAddress;
    private int integral;
    private int exp;
    private int reputationPoint;
    private Timestamp registerDate;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getReputationPoint() {
        return reputationPoint;
    }

    public void setReputationPoint(int reputationPoint) {
        this.reputationPoint = reputationPoint;
    }

    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mailAddress='" + mailAddress + '\'' +
                ", integral=" + integral +
                ", exp=" + exp +
                ", reputationPoint=" + reputationPoint +
                ", registerDate=" + registerDate +
                ", status='" + status + '\'' +
                '}';
    }
}
