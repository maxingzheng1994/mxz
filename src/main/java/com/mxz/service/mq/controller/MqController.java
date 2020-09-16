package com.mxz.service.mq.controller;

import com.mxz.common.utils.MQUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * mq发送消息测试
 *
 * @author mxz on 2020/09/16 20:10
 */
@RestController
@RequestMapping("/mq")
public class MqController {

    @RequestMapping("/send")
    public void mq(@RequestParam String name) {
        MQUtils.sendMsg(name, "TopicTest", "tagA", "");
    }
}
