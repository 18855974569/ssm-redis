package com.wjx.blog.article.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.wjx.blog.article.mapper.ArticleMapper;
import com.wjx.blog.article.pojo.Article;
import com.wjx.blog.article.services.ArticleServices;


@Service
public class ArticleServicesImpl implements ArticleServices {

	@Autowired
	private ArticleMapper articleMapper;

	public List<Article> queryArticleList() {

		return articleMapper.queryArticleList();
	}

	public Article queryArticleById(Article article) {

		return articleMapper.queryArticleById(article);
	}

	public int addArticle(Article article) {

		return articleMapper.addArticle(article);
	}

	public int updateArticle(Article article) {

		return articleMapper.updateArticle(article);
	}

	@Transactional
	public int deleteArticle(List<Article> articles) {

		int len = 0;
		try {
			for (Article article : articles) {
				articleMapper.deleteArticle(article);
				len++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
		}
		return len;
	}

}
