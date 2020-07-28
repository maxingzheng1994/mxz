package com.mxz.service.video.controller;

import com.mxz.service.video.model.Video;
import com.mxz.service.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/07/25 17:31
 */
@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @RequestMapping("/list")
    public List<Video> list() {
        return videoService.list();
    }

    @RequestMapping("/save")
    public boolean save(@RequestBody Video video) {
        return videoService.save(video);
    }

}
