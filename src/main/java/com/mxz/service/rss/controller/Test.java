package com.mxz.service.rss.controller;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/08/27 17:48
 */
public class Test {
    public static void main(String[] args) {
        Son son = new Son();
        System.out.println(son.c);
    }
    public static void main1(String[] args) {
        String s = "kevingrace,zhanjiahui,sunshine-2015,foreach-break,rollenholt,aigongsi,JavaArchitect,haore147,xiaoyangjia,gaochundong,heyboom,scy251147,Anker,xuwc,dennyzhangdd,dolphin0520,williamjie,throwable,jpfss,xuanku,aspirant,xueweihan,panwenbin-logs,hongdada,haizai,xrq730,xishuai,vamei,chenssy,hafiz,java-zhao,rjzheng,felixzh,hustskyking,java-my-life,skywang12345,CarpenterLee,xinzhao,davidwang456,sjlian,phinecos";
        String[] split = s.split(",");
        for (int i = 0; i < split.length; i++) {
            String str = "<outline text=\"%s\" type=\"rss\" htmlUrl=\"https://www.cnblogs.com/skywang12345/p/3603935.html\" xmlUrl=\"https://mxz1.oss-cn-shenzhen.aliyuncs.com/%s.xml\"/>";
            String format = String.format(str, split[i], split[i]);
            System.out.println(format);
        }
        // phinecos// &#x8; &#x1c; &#x1;
    }
}
