package com.wjx.blog.mapper;

import java.util.List;

import com.wjx.blog.pojo.Blog;

public interface BlogMapper {
	public List<Blog> queryBlogList();

	public Blog queryBlogById(Blog blog);

	public int addBlog(Blog blog);

	public int updateBlog(Blog blog);

	public int deleteBlog(Blog blog);
}
