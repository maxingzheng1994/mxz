package com.mxz.common.utils;

import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author mxz
 * @Date 2020/8/23 12:43
 **/
@XStreamConverter(value= ToAttributedValueConverter.class, strings={"value"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content {
    private String type = "html";
    private String value;

    public Content(String value) {
        this.value = value;
    }
}
