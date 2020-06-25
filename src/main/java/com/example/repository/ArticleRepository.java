package com.example.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

@Repository
public class ArticleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		return article;
	};
	private static final RowMapper<Article> ARTICLE_JOIN_COMMENT_ROW_MAPPER=(rs,i)->{
		Article article=new Article();
		Comment comment=new Comment();
		List<Comment> commentList=new ArrayList();
		comment.setId(rs.getInt("comm_id"));
		comment.setName(rs.getString("comm_name"));
		comment.setContent(rs.getString("comm_content"));
		comment.setArticleId(rs.getInt("article_id"));
		commentList.add(comment);
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		article.setCommentList(commentList);
		return article;
	};

	public List<Article> findJoin(){
		String sql="SELECT a.id,a.name,a.content,c.id as comm_id,c.name as comm_name,c.content as comm_content, article_id "
				+ "FROM articles AS a LEFT OUTER JOIN comments AS c ON a.id = c.article_id ORDER BY a.id DESC";
		try {
			List<Article> articleList = template.query(sql, ARTICLE_JOIN_COMMENT_ROW_MAPPER);
			return articleList;
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public List<Article> findAll() {
		String sql = "SELECT id,name,content FROM articles ORDER BY id DESC";
		try {
			List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
			return articleList;
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			// TODO: handle exception
			return null;
		}
	}
	public void insert(Article article) {
		String sql="INSERT INTO articles(name, content) VALUES(:name,:content)";
		SqlParameterSource  param=new MapSqlParameterSource().addValue("name", article.getName()).addValue("content", article.getContent());
		template.update(sql, param);
	}
	public void deleteById(Integer id) {
		String sql="DELETE FROM articles WHERE id=:id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
}
