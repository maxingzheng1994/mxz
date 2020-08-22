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
        String str = "<feed xmlns=\"http://www.w3.org/2005/Atom\">\n" +
                "  <title type=\"text\">博客园_skywang12345</title></feed>";
        Feed feed = new Feed();
        feed.setXmlns("sadas");
        Title title = new Title();
        title.setText("sadsssssa");
        feed.setTitle(title);
        ArrayList<Entry> objects = new ArrayList<>();
        feed.setEntryList(objects);
        Object o = XmlUtils.toXml(feed);
        Object od= XmlUtils.toBean(Feed.class, str);
        System.out.println(o);
        System.out.println(od);
    }
}
