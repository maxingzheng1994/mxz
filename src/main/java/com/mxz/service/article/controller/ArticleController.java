package com.mxz.service.article.controller;

import com.mxz.service.article.model.Article;
import com.mxz.service.article.service.ArticleService;
import com.youbenzi.mdtool.tool.MDTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.expression.Maps;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/08/12 11:51
 */
@Controller
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("articles", articleService.list());
        return "article/articles_list";
    }

    @RequestMapping("/listLimit/{path}")
    public boolean listLimit(Model model, @PathVariable(required = false) String path) {
        if (path == null) {

        }
        return articleService.init();
    }


    @RequestMapping("/init")
    @ResponseBody
    public boolean init() {
        return articleService.init();
    }

    @RequestMapping("/{id}")
    public void list(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        Article article = articleService.getById(id);
        FileReader reader = new FileReader(article.getPath());
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            // ... handle IO exception
        }
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        //这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859
        response.setCharacterEncoding("UTF-8");

        String html = MDTool.markdown2Html(new File(article.getPath()));
        response.getWriter().write(html);
//        model.addAttribute("article", article);
//        model.addAttribute("text", sb.toString());
//        return "article/article";
    }

}
