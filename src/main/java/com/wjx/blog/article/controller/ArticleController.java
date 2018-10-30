package com.wjx.blog.article.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wjx.blog.article.pojo.Article;
import com.wjx.blog.article.services.ArticleServices;


@Controller
public class ArticleController {
	
	
	@Autowired
	private ArticleServices articleServices;
	
	
	@RequestMapping(value="/queryArticleList",method = RequestMethod.GET)
	public String queryArticleList(HttpServletRequest request){
		
		List<Article> list = articleServices.queryArticleList();
		request.setAttribute("list", list);
		return "blog/articleList";
	}
	
	@RequestMapping(value="/queryArticleDetail",method = RequestMethod.GET)
	public String queryArticleDetail(HttpServletRequest request){
		
		return "blog/articleDetail";
	}
}
