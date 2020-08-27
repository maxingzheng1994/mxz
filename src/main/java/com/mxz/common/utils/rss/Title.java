package com.mxz.common.utils.rss;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/08/22 16:57
 */
//@XmlRootElement(name = "title")
//@XmlAccessorType(XmlAccessType.PROPERTY)
@XStreamConverter(value= ToAttributedValueConverter.class, strings={"value"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Title {
    @XStreamAsAttribute
    private String type = "text";

    private String value;

    public Title(String value) {
        this.value = value;
    }
}
