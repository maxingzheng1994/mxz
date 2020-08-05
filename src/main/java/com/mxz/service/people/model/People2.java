package com.mxz.service.people.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/08/05 16:47
 */
@Data
@TableName(value = "PEOPLE_2")
public class People2 {
    @TableId(value = "USE_ID", type = IdType.AUTO)
    private Integer useId;

    @TableField(value = "USE_NAME")
    private String useName;

    @TableField(value = "USE_SEX")
    private String useSex;

    @TableField(value = "USE_AGE")
    private Short useAge;

    @TableField(value = "USE_ID_NO")
    private String useIdNo;

    @TableField(value = "USE_PHONE_NUM")
    private String usePhoneNum;

    @TableField(value = "USE_EMAIL")
    private String useEmail;

    @TableField(value = "CREATE_TIME")
    private Date createTime;

    @TableField(value = "MODIFY_TIME")
    private Date modifyTime;

    @TableField(value = "USE_STATE")
    private String useState;

    public static final String COL_USE_ID = "USE_ID";

    public static final String COL_USE_NAME = "USE_NAME";

    public static final String COL_USE_SEX = "USE_SEX";

    public static final String COL_USE_AGE = "USE_AGE";

    public static final String COL_USE_ID_NO = "USE_ID_NO";

    public static final String COL_USE_PHONE_NUM = "USE_PHONE_NUM";

    public static final String COL_USE_EMAIL = "USE_EMAIL";

    public static final String COL_CREATE_TIME = "CREATE_TIME";

    public static final String COL_MODIFY_TIME = "MODIFY_TIME";

    public static final String COL_USE_STATE = "USE_STATE";
}