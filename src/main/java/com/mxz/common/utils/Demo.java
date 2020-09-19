package com.mxz.common.utils;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/09/17 10:46
 */
public class Demo {
    public static void main(String[] args) {
        String url = "http://www.hnrsks.com/LinkPage/cjcx_dl.aspx?state=1&Id=4870";
        String s = HttpUtils.get("http://www.hnrsks.com/");
        System.out.println("s = " + s);
    }
}
