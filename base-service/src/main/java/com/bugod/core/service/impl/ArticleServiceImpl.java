package com.bugod.core.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bugod.core.mapper.ArticleMapper;
import com.bugod.core.service.IArticleService;
import com.bugod.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = "list")
    @Override
    public List<Article> list(String title) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StrUtil.isNotBlank(title), Article::getTitle, title);
        List<Article> result = list(queryWrapper);
        return result;
    }
}
