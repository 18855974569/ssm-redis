package com.wjx.blog.article.mapper;

import java.util.List;

import com.wjx.blog.article.pojo.Article;

public interface ArticleMapper {
	public List<Article> queryArticleList();

	public Article queryArticleById(Article article);

	public int addArticle(Article article);

	public int updateArticle(Article article);

	public int deleteArticle(Article article);
}
