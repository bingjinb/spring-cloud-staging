package com.bugod.core.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bugod.core.mapper.ArticleMapper;
import com.bugod.core.service.IArticleService;
import com.bugod.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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


    /**
     * 缓存名字为 list, 缓存key：title值，结果如果是空值，则不缓存
     * @param title
     * @return
     */
//    @Cacheable(value = "list", key = "#title", unless="#result == null")
    @Override
    public List<Article> list(String title) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StrUtil.isNotBlank(title), Article::getTitle, title);
        List<Article> result = list(queryWrapper);
        return result;
    }

    /**
     * 每次保存，都清除掉，缓存名字为 list的所有值
     * @param po
     * @return
     */
    @CacheEvict(value = "list", key = "#po.title", allEntries = true)
    @Override
    public boolean save(Article po) {
        return super.save(po);
    }

    /**
     * 先查缓存，存在则不错处理，不存在，则更新所有list对应key的缓存。
     * @param po
     * @return
     */
    @CachePut(value = "list", key = "#po.title")
    @Override
    public boolean update(Article po) {
        return super.update(po, new LambdaQueryWrapper<>());
    }

}
