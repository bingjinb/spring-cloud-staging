package com.bugod.core.controller;

import com.alibaba.fastjson.JSONObject;
import com.bugod.annotation.Limit;
import com.bugod.core.service.IArticleService;
import com.bugod.entity.Article;
import com.bugod.entity.pojo.ResultWrapper;
import com.bugod.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags="文章")
@RestController
@RequestMapping("/api/article")
public class ArticleController extends BaseController {
	@Autowired
	private IArticleService articleService;

	@Autowired
	RedisUtil redisUtil;

	// @EmailMonitor(email = "xxx@163.com")
//	@Log(description = "文章列表")
	@Limit(name = "文章列表", period = 100, count = 5)
	@ApiOperation(value = "列表", notes = "列表")
	@GetMapping("/list")
	public ResultWrapper<List<Article>> list(String title) {

		List<Article> result = articleService.list(title);
		// @EmailMonitor 注解异常邮件通知测试
		// int i = 100 / 0;
		return success(result);
	}

	@ApiOperation(value = "保存", notes = "保存")
	@PostMapping("/save")
	public ResultWrapper<Boolean> save(@RequestParam Integer userId, @RequestParam String title, @RequestParam String content) {
		Article article = new Article().setUserId(userId).setTitle(title).setContent(content);
		Boolean result = articleService.save(article);
		return success(result);
	}

	@ApiOperation(value = "更新", notes = "更新")
	@PostMapping("/update")
	public ResultWrapper<Boolean> update(@RequestParam Integer userId, @RequestParam String title, @RequestParam String content) {
		Article article = new Article().setUserId(userId).setTitle(title).setContent(content);
		Boolean result = articleService.update(article);
		return success(result);
	}


	@ApiOperation(value = "fetch-缓存", notes = "fetch-缓存")
	@GetMapping("/fetch")
	public ResultWrapper set() {
		Article article = new Article();
		redisUtil.set("hello", "你好");
		redisUtil.set("PO", article.setTitle("PO").setUserId(111).toString());
		log.info(redisUtil.get("hello")+" , " + JSONObject.toJSON(redisUtil.get("PO")));
		return success();
	}


}
