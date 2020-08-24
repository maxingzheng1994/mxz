package com.mxz.common.utils;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author mxz on 2020/08/24 10:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link {
    @XStreamAsAttribute
    private String type = "text/html";

    @XStreamAsAttribute
    private String rel = "alternate";
    @XStreamAsAttribute
    private String href;

    public Link(String href) {
        this.href = href;
    }
}
