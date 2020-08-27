package com.mxz.service.rss.bokeyuan.model;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mxz on 2020/08/27 14:29
 */
@Data
public class BlogYuanVO implements Serializable {
    /**
     * 作者
     */
    private String author;
    /**
     * 链接
     */
    private String url = "http://www.cnblogs.com/%s/default.html?page=%d";
    /**
     * 页码
     */
    private Integer pageNo;

    private Integer pageCount;

    public BlogYuanVO(String author, Integer pageCount) {
        this.author = author;
        this.pageCount = pageCount;
    }

    public List<String> getLinkList() {
        List<String> list = Lists.newArrayList();
        if (pageCount != null) {
            for (int i = 1; i <= pageCount; i++) {
                list.add(String.format(url, author, i));
            }
        }
        return list;
    }

}
