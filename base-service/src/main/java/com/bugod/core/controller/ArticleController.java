package com.bugod.core.controller;

import com.bugod.core.entity.Article;
import com.bugod.core.service.IArticleService;
import com.bugod.entity.ResultWrapper;
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

	@ApiOperation(value = "列表", notes = "列表")
	@GetMapping("/list")
	public ResultWrapper<List<Article>> list() {
		List<Article> result = articleService.list();
		return success(result);
	}

	@ApiOperation(value = "保存", notes = "保存")
	@PostMapping("/save")
	public ResultWrapper<Boolean> list(@RequestParam Integer userId, @RequestParam String title, @RequestParam String content) {
		Article article = new Article().setUserId(userId).setTitle(title).setContent(content);
		Boolean result = articleService.save(article);
		return success(result);
	}
}
