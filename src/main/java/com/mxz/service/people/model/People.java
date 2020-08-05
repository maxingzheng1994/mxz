package com.mxz.service.people.model;

import java.util.Date;
import lombok.Data;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/08/05 16:29
 */
@Data
public class People {
    private Integer useId;

    private String useName;

    private String useSex;

    private Short useAge;

    private String useIdNo;

    private String usePhoneNum;

    private String useEmail;

    private Date createTime;

    private Date modifyTime;

    private String useState;
}