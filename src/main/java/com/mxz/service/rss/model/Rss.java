package com.mxz.service.rss.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/08/27 16:18
 */
@Data
@TableName(value = "RSS")
public class Rss {
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "NAME")
    private String name;

    @TableField(value = "TYPE")
    private String type;

    @TableField(value = "RSSCONTENT")
    private String rsscontent;

    public static final String COL_ID = "ID";

    public static final String COL_NAME = "NAME";

    public static final String COL_TYPE = "TYPE";

    public static final String COL_RSSCONTENT = "RSSCONTENT";
}