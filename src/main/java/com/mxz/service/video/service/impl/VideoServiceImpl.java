package com.mxz.service.video.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mxz.service.video.mapper.VideoMapper;
import com.mxz.service.video.model.Video;
import com.mxz.service.video.service.VideoService;
/**
 * TODO: 注释.
 *
 * @author mxz on 2020/07/25 17:29
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService{

    @Override
    public int batchInsert(List<Video> list) {
        return baseMapper.batchInsert(list);
    }
}
