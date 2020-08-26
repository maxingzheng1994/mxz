package com.mxz.common.utils;

import java.util.List;
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

}
