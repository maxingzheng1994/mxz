package com.mxz.service.rss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.mxz.service.rss.constatns.RssConstants;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mxz.service.rss.mapper.RssMapper;
import java.util.List;
import com.mxz.service.rss.model.Rss;
import com.mxz.service.rss.service.RssService;
/**
 * TODO: 注释.
 *
 * @author mxz on 2020/08/27 16:18
 */
@Service
public class RssServiceImpl extends ServiceImpl<RssMapper, Rss> implements RssService{

    @Override
    public int updateBatch(List<Rss> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int batchInsert(List<Rss> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public Rss selectByNameAndType(String name, String type) {
        QueryWrapper<Rss> rssQueryWrapper = new QueryWrapper<>();
        HashMap<String, String> query = Maps.newHashMap();
        query.put("name", name);
        query.put("type", type);
        rssQueryWrapper.allEq(query);
        Rss one = this.getOne(rssQueryWrapper);
        return one;
    }
}
