package com.mxz.common.utils.rss;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/08/22 16:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("feed")
public class Feed {
    @XStreamAsAttribute
    private String xmlns = "http://www.w3.org/2005/Atom";
    private Title title;
    @XStreamImplicit(itemFieldName="entry")
    private List<Entry> entryList;

    private void setRssName(String rssName) {

    }
    public void make() {
        
    }
}
