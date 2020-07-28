package com.mxz.service.video.service;

import java.util.List;
import com.mxz.service.video.model.Video;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * TODO: 注释.
 *
 * @author mxz on 2020/07/25 17:29
 */
public interface VideoService extends IService<Video>{


    int batchInsert(List<Video> list);

}
