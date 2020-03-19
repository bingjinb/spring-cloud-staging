package com.bugod.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bugod.core.entity.Article;
import com.bugod.core.mapper.ArticleMapper;
import com.bugod.core.service.IArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
