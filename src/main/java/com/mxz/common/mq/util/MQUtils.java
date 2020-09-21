
package com.mxz.common.mq.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * mq工具类
 *
 * @author mxz on 2020/09/21 15:31
 */
public class MQUtils {


    public static <T> T from(String valueAsJSON, Class<T> clazz) {
        try {
            return JSONObject.parseObject(valueAsJSON, clazz);
        } catch (JSONException ex) {
            throw ex;
        }
    }

    public static <T> String to(T value) {
        try {
            return JSONObject.toJSONString(value);
        } catch (JSONException ex) {
            throw ex;
        }
    }
}
