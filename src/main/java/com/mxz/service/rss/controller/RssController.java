package com.mxz.service.rss.controller;

import com.google.common.io.Files;
import com.mxz.common.utils.MatcherUtils;
import com.mxz.common.utils.XmlUtils;
import com.mxz.common.utils.rss.Feed;
import com.mxz.service.rss.constatns.RssConstants;
import com.mxz.service.rss.model.Rss;
import com.mxz.service.rss.service.RssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/08/22 14:52
 */
@Controller
@RequestMapping("/rss")
public class RssController {
    @Autowired
    private RssService rssService;
    
    @RequestMapping("/{name}")
    public void rss3(@PathVariable String name, HttpServletResponse response) throws Exception {
        Rss rss = rssService.selectByNameAndType(name, RssConstants.BLOG_YUAN);
        if (rss == null) {
            rssInit(name, response);
        } else {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(rss.getRsscontent());
        }
    }

    @RequestMapping("/init/{name}")
    public void rssInit(@PathVariable String name, HttpServletResponse response) throws Exception {
        Feed feed = MatcherUtils.blogYuan(name);
        String o = XmlUtils.toXml(feed);
        Files.write(o.getBytes(), new File("/data/rss/"+name+".xml"));
        Rss rssDb = rssService.selectByNameAndType(name, RssConstants.BLOG_YUAN);
        if (rssDb != null) {
            rssService.removeById(rssDb.getId());
        }
        Rss rss = new Rss();
        rss.setName(name);
        rss.setType(RssConstants.BLOG_YUAN);
        rss.setRsscontent(o);
        rssService.save(rss);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(o);
    }

    public static void main(String[] args) throws IOException {
        String s = "fzz9";
        String[] split = s.split(",");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i] + i);
            batchShencheng(split[i]);
        }
    }
    public static void batchShencheng(String name) throws IOException {
        Feed feed = MatcherUtils.blogYuan(name);
        String o = XmlUtils.toXml(feed);
        Files.write(o.getBytes(), new File("D:/H2/"+name+".xml"));
    }
}

