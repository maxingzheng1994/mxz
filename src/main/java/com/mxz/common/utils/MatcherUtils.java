package com.mxz.common.utils;

import com.mxz.common.utils.rss.Feed;
import com.mxz.common.utils.rss.FeedBuilder;
import com.mxz.service.rss.bokeyuan.model.BlogYuanVO;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/08/24 10:54
 */
public class MatcherUtils {
    static Pattern urlP = Pattern.compile("<a.*class=\"postTitle2 vertical-middle\".*href=\"(.*)\">");
    static Pattern titleTopP = Pattern.compile("<a.*class=\"postTitle2 vertical-middle\".*\n" +
            ".*<span>\n" +
            "(.*\n" +
            ".*)\n" +
            ".*</span>");
    static Pattern titleP = Pattern.compile("<a.*class=\"postTitle2 vertical-middle\".*\n" +
            ".*<span>\n" +
            "(.*)\n" +
            ".*</span>");

    public static Feed blogYuan(String blogName) {
        BlogYuanVO skywang12345 = new BlogYuanVO(blogName, 40);
        List<String> linkList = skywang12345.getLinkList();

        FeedBuilder feedBuilder = new FeedBuilder().setRssName(blogName);
        for (String link : linkList) {
            List<String> url1 = matchAll(link, urlP);
            List<String> name1 = matchAll(link, titleTopP, titleP);
            if (url1.isEmpty()) {
                break;
            }

            for (int i = 0; i < url1.size(); i++) {
                feedBuilder.addNewEntry(url1.get(i), name1.get(i), "");
            }
        }
        return feedBuilder.build();
    }
    /**
     *
     * @param url 链接
     * @param urlP url匹配器
     * @return
     */
    public static List<String> matchAll(String url, Pattern urlP) {
        String s = HttpUtils.get(url);
        Matcher urlMatcher = urlP.matcher(s);
        List<String> result = new ArrayList<>();
        while (urlMatcher.find()) {
            String group = urlMatcher.group(1);
            result.add(group);
        }
        return result;
    }
    public static List<String> matchAll(String url, Pattern... urlP) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < urlP.length; i++) {
            String s = HttpUtils.get(url);
            Matcher urlMatcher = urlP[i].matcher(s);
            while (urlMatcher.find()) {
                String group = urlMatcher.group(1);
                result.add(group);
            }
        }
        return result;
    }
}
