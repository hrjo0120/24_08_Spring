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
	// 게시글 추가
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		Article article = writeArticle(title, body);

		return article;
	}

	// 모든 게시글 보기
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {

		return articles;
	}
	
	// 게시글 삭제
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		Article article = getArticleById(id);

		if (article == null) {
			return id + "번 글은 없습니다";
		}

		articles.remove(article);

		return id + "번 글이 삭제됨";

	}

	// 게시글 수정
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public Object doModify(int id, String title, String body) {

		Article article = getArticleById(id);

		if (article == null) {
			return id + "번 글은 없습니다";
		}

		article.setTitle(title);
		article.setBody(body);

		return article;
//		article을 붙이는 위치에 따라서 실행 여부가 갈림
//		return article + id + "번 글이 수정되었습니다";		// article을 id 앞에 붙이면 실행되지 않음
//		return id + "번 글이 수정되었습니다" + article;		// article을 String 뒤에 붙여야 실행됨

	}
	
	// 게시글 상세보기
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public Object getArticle(int id) {

		Article article = getArticleById(id);

		if (article == null) {
			return id + "번 글은 없음";
		}

		return article;
	}


}