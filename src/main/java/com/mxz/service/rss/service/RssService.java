package com.mxz.service.rss.service;

import java.util.HashMap;
import java.util.List;
import com.mxz.service.rss.model.Rss;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * TODO: 注释.
 *
 * @author mxz on 2020/08/27 16:18
 */
public interface RssService extends IService<Rss>{


    int updateBatch(List<Rss> list);

    int batchInsert(List<Rss> list);

    Rss selectByNameAndType(String name, String type);
    }
