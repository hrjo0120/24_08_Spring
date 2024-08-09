package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.vo.Article;

@Controller
public class UserArticleController {

	@Autowired
	private ArticleService articleService;

	// 게시글 추가
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		Article article = articleService.writeArticle(title, body);

		return article;
	}

	// 모든 게시글 보기
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {

		return articleService.articles;
	}

	// 게시글 삭제
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return id + "번 글은 없습니다";
		}

		articleService.deleteArticle(id);

		return id + "번 글이 삭제됨";

	}

	// 게시글 수정
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public Object doModify(int id, String title, String body) {

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return id + "번 글은 없습니다";
		}

		articleService.modifyArticle(id, title, body);

		return article;
	}

	// 게시글 상세보기
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public Object getArticle(int id) {

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return id + "번 글은 없음";
		}

		return article;
	}

}