package com.mxz.service.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mxz.service.article.model.Article;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/08/12 11:50
 */
public interface ArticleMapper extends BaseMapper<Article> {
    int updateBatch(List<Article> list);

    int batchInsert(@Param("list") List<Article> list);

    int batchDelete();
}