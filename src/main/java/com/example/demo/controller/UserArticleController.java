package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.vo.Article;

@Controller
public class UserArticleController {

	int lastArticleId = 0;
	List<Article> articles;

	// 생성자
	public UserArticleController() {
		articles = new ArrayList<>();

		// 게시글 테스트 데이터 생성
		makeTestData();
	}

	// 서비스 메서드
	private void makeTestData() {
		for (int i = 1; i <= 10; i++) {
			String title = "제목" + i;
			String body = "내용" + i;

			writeArticle(title, body);
		}
	}

	private Article writeArticle(String title, String body) {
		int id = lastArticleId + 1;
		Article article = new Article(id, title, body);
		articles.add(article);
		lastArticleId++;
		return article;
	}

	private Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	// 액션 메소드
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		Article article = writeArticle(title, body);

		return article;
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {

		return articles;
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		Article article = getArticleById(id);

		if (article == null) {
			return id + "번 글은 없습니다";
		}

		// 이렇게 하면 articles의 위치상 삭제되는 것
		// 원하는 것이 지워지지 않을 수 있음
		// articles.remove(id - 1);

		articles.remove(article);

		return id + "번 글이 삭제됨";

	}

}