package com.mxz.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
*@Description  
*@author mxz
*2018-07-28
**/
@Entity
public class Token {
    @Id
    @GeneratedValue
    private Long pkId;
    private String valueField;
    private Long userId;
    private String deviceId;
    private Date loginDate;
    private Date expireDate;
     
    public Long getPkId() {
        return pkId;
    }
    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }
    public String getValueField() {
        return valueField;
    }
    public void setValueField(String valueField) {
        this.valueField = valueField;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public Date getLoginDate() {
        return loginDate;
    }
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
    public Date getExpireDate() {
        return expireDate;
    }
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
