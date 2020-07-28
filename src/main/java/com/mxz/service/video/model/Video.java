package com.mxz.service.video.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/07/25 17:29
 */
@ApiModel(value="com-mxz-service-video-model-Video")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "video")
public class Video {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "url")
    @ApiModelProperty(value="")
    private String url;
}