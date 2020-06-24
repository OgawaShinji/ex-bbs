package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Comment;
import com.example.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	public List<Comment> findByArticleId(Integer articleId) {
		List<Comment> commentList = commentRepository.findByArticleId(articleId);
		return commentList;
	}
	public void insert(Comment comment) {
		commentRepository.insert(comment);
	}
	public void deleteByArticleId(Integer articleId) {
		commentRepository.deleteByArticleId(articleId);
	}
}
