package com.mxz.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author mxz
 * @Date 2020/8/23 12:46
 **/
public class FeedBuilder {
    private Feed feed;

    public FeedBuilder(Feed feed) {
        this.feed = feed;
    }
    public FeedBuilder() {
        this.feed = new Feed();
    }

    public FeedBuilder setRssName(String name) {
        Title title = new Title(name);
        this.feed.setTitle(title);
        return this;
    }
    public FeedBuilder addNewEntry(String link, String name, String contentStr) {
        Id id = new Id(link);
        Title title = new Title(name);
        Link link1 = new Link(link);
        Content content = new Content(contentStr);
        Entry entry = new Entry(id, title, link1, content);
        List<Entry> entryList = this.feed.getEntryList();
        if (entryList == null || entryList.isEmpty()) {
            entryList = new ArrayList<>();
            this.feed.setEntryList(entryList);
        }
        entryList.add(entry);
        return this;
    }

    public Feed build() {
        return this.feed;
    }
}
