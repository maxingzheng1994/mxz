package com.mxz.model;

import lombok.Data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
*@Description  
*@author mxz
*2018-07-28
**/
@Entity
@Table(name = "token")
@Data
public class Token extends BaseVO {

    private String valueField;
    private Long userId;
    private String deviceId;
    private Date loginDate;
    private Date expireDate;
     
}
