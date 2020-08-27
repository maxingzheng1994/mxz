package com.mxz.service.rss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mxz.service.rss.model.Rss;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/08/27 16:18
 */
public interface RssMapper extends BaseMapper<Rss> {
    int updateBatch(List<Rss> list);

    int batchInsert(@Param("list") List<Rss> list);
}