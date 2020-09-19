package com.mxz.common.mq;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * mq 配置
 *
 * @author mxz on 2020/09/19 17:30
 */
@ConfigurationProperties(prefix = "com.mxz.mq")
public class MQConfigProperties {

    private Map<String, String> consumer = new HashMap<String, String>();
}
