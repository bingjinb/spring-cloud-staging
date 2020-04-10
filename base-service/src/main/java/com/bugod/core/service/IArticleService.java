package com.bugod.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bugod.entity.Article;

import java.util.List;

public interface IArticleService extends IService<Article> {

    List<Article> list(String title);
}
