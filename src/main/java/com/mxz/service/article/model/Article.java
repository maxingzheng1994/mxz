package com.mxz.service.article.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author mxz on 2020/08/12 11:50
 */
@Data
@TableName(value = "ARTICLE")
@Builder
public class Article {
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "NAME")
    private String name;

    @TableField(value = "path")
    private String path;

    public static final String COL_ID = "ID";

    public static final String COL_NAME = "NAME";

    public static final String COL_PATH = "path";
}