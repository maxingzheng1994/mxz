package com.mxz.service.article.service.impl;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import com.mxz.service.article.mapper.ArticleMapper;
import com.mxz.service.article.model.Article;
import com.mxz.service.article.service.ArticleService;
/**
 * TODO: 注释.
 *
 * @author mxz on 2020/08/12 11:50
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService{

    @Override
    public int updateBatch(List<Article> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int batchInsert(List<Article> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public boolean init() {
        File file = new File("/data");
        ArrayList<File> files = new ArrayList<>();
        deepFiles(file,files);
        List<Article> articles = new ArrayList<>();
        for (File file1 : files) {
            articles.add(Article.builder().name(file1.getName().split(".md")[0]).path(file1.getPath()).build());
        }
        baseMapper.batchDelete();
        return saveBatch(articles);
    }

    public static void main(String[] args) {


    }

    public static void deepFiles(File file, List<File> list) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                deepFiles(file1, list);
            }
        } else if (file.isFile()) {
            if (file.getName().endsWith(".md")) {
                list.add(file);
            }
        }
    }
}
