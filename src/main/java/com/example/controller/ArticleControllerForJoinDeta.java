package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.service.ArticleService;
import com.example.service.CommentService;

@Controller
@RequestMapping("/ex-bbs2")
public class ArticleControllerForJoinDeta {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private CommentService commentService;

	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleService.findJoin();
		model.addAttribute("articleList", articleList);
		return "bbs";
	}
	@RequestMapping("/articleInsert")
	public String insertArticle(ArticleForm form,Model model) {
		Article article=new Article();
		article.setName(form.getName());
		article.setContent(form.getContent());
		articleService.insert(article);
		return index(model);
	}
	@RequestMapping("/commentInsert")
	public String insertComment(CommentForm form, Model model) {
		Comment comment=new Comment();
		comment.setName(form.getName());
		comment.setContent(form.getContent());
		comment.setArticleId(Integer.parseInt(form.getArticleId()));
		commentService.insert(comment);
		return index(model);
	}
	@RequestMapping("/articleDelete")
	public String delete(Integer id,Model model) {
		commentService.deleteByArticleId(id);
		articleService.deleteById(id);
		return index(model);
	}
}
