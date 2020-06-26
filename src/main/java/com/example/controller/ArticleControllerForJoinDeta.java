package com.example.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}
	

	@RequestMapping("")
	public String indexJoin(Model model) {
		List<Article> articleList = articleService.findJoin();
		model.addAttribute("articleList", articleList);
		return "bbs";
	}
	@RequestMapping("/articleInsert")
	public String insertArticle(@Validated ArticleForm form, BindingResult result,Model model) {
		if(result.hasErrors()) {
			return indexJoin(model);
		}
		Article article=new Article();
		article.setName(form.getName());
		article.setContent(form.getContent());
		articleService.insert(article);
		return "redirect:/ex-bbs2";
	}
	@RequestMapping("/commentInsert")
	public String insertComment(@Validated CommentForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("bindingArticleId", Integer.parseInt(form.getArticleId()));
			return indexJoin(model);
		}
		Comment comment=new Comment();
		comment.setName(form.getName());
		comment.setContent(form.getContent());
		comment.setArticleId(Integer.parseInt(form.getArticleId()));
		commentService.insert(comment);
		return "redirect:/ex-bbs2";
	}
	@RequestMapping("/articleDelete")
	public String delete(Integer id,Model model) {
		//commentService.deleteByArticleId(id);
		articleService.deleteById(id);
		return indexJoin(model);
	}
}
