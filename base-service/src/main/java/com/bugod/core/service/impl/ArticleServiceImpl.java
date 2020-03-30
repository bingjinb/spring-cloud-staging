package com.bugod.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bugod.core.mapper.ArticleMapper;
import com.bugod.core.service.IArticleService;
import com.bugod.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Override
    public List<Article> list(Wrapper<Article> queryWrapper) {
        log.info(" ------> traceId  controller -> service -> mapper test");
        return super.list(queryWrapper);
    }
}
