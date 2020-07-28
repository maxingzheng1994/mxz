package com.mxz.service.video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mxz.service.video.model.Video;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/07/25 17:29
 */
public interface VideoMapper extends BaseMapper<Video> {
    int batchInsert(@Param("list") List<Video> list);
}