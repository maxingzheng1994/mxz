package com.mxz.common.utils;

import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/08/22 16:53
 */
public class XmlUtils {
    public static Object toBean(Class<?> clazz, String xml) {
        Object xmlObject = null;
        XStream xstream = new XStream();
        xstream.processAnnotations(clazz);
        xstream.autodetectAnnotations(true);
        xmlObject= xstream.fromXML(xml);
        return xmlObject;
    }

    public static String toXml(Object obj) {
        String xmlObject = null;
        XStream xstream = new XStream();
        xstream.autodetectAnnotations(true);
        xmlObject= xstream.toXML(obj);
        return xmlObject;
    }

    public static void main(String[] args) {
        Feed feed = new FeedBuilder().setRssName("博客园_skywang12345").addNewEntry("http://www.cnblogs.com/skywang12345/p/3711532.html", "sds", "wenzhang").build();
        Object o = XmlUtils.toXml(feed);
        System.out.println(o);
    }
}
