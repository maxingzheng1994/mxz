package com.mxz.service.article.service;

import java.util.List;
import com.mxz.service.article.model.Article;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * TODO: 注释.
 *
 * @author mxz on 2020/08/12 11:50
 */
public interface ArticleService extends IService<Article>{


    int updateBatch(List<Article> list);

    int batchInsert(List<Article> list);

    boolean init();
    }
