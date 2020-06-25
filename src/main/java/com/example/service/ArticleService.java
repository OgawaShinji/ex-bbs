package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.repository.ArticleRepository;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	public List<Article> findJoin() {
		List<Article> articleListFromRepository = articleRepository.findJoin();
		List<Article> articleList = new ArrayList<>();
		List<Comment> commentList = new ArrayList<>();
		for (int i = 0; i < articleListFromRepository.size(); i++) {
			if (i == 0) {
				commentList.add(articleListFromRepository.get(i).getCommentList().get(0));
				continue;
			}
			if (articleListFromRepository.get(i).getId() == articleListFromRepository.get(i - 1).getId()) {
				commentList.add(articleListFromRepository.get(i).getCommentList().get(0));
				if (i == articleListFromRepository.size() - 1) {
					Article article = articleListFromRepository.get(i - 1);
					article.setCommentList(commentList);
					articleList.add(article);
				}
				continue;
			} else {
				Article article = articleListFromRepository.get(i - 1);
				article.setCommentList(commentList);
				articleList.add(article);
				commentList = new ArrayList<Comment>();
				commentList.add(articleListFromRepository.get(i).getCommentList().get(0));
				if (i == articleListFromRepository.size() - 1) {
					article = articleListFromRepository.get(i);
					articleList.add(article);
				}
				continue;
			}
		}
		return articleList;
	}

	public List<Article> findAll() {
		List<Article> articleList = articleRepository.findAll();
		return articleList;
	}

	public void insert(Article article) {
		articleRepository.insert(article);
	}

	public void deleteById(Integer id) {
		articleRepository.deleteById(id);
	}
}
