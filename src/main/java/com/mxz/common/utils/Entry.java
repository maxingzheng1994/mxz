package com.mxz.common.utils;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/08/22 17:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("entry")
public class Entry {
    private Id id;
    private Title title;
    private Link link;
    private Content content;
}
