package com.wjx.user.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.util.PageUtil;
import com.wjx.user.pojo.User;
import com.wjx.user.services.UserServices;

@Controller
@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserController {

	@Autowired
	private UserServices services;

	private RedisTemplate redisTemplate;

	/**
	 * 最近使用spring-data-redis RedisTemplate
	 * 操作redis时发现存储在redis中的key不是设置的string值，前面还多出了许多类似\xac\xed\x00\x05t\x00这种字符串，如下
	 * 
	 * 127.0.0.1:6379> keys * 1) "\xac\xed\x00\x05t\x00\x04pass" 2)
	 * "\xac\xed\x00\x05t\x00\x04name" 3) "name"
	 * 
	 * @param redisTemplate
	 */
	@Autowired(required = false)
	public void setRedisTemplate(RedisTemplate redisTemplate) {
		//先序列化redis的实例
		RedisSerializer stringSerializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(stringSerializer);
		redisTemplate.setValueSerializer(stringSerializer);
		redisTemplate.setHashKeySerializer(stringSerializer);
		redisTemplate.setHashValueSerializer(stringSerializer);
		System.out.println("redis实例是*************************************：" + redisTemplate);
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public String userList() {
		return "userList";
	}

	/**
	 * 查询数据
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryUser", method = RequestMethod.GET)
	private PageUtil queryUserList(HttpServletRequest request) {
		List<User> list = services.queryUserList();
		request.setAttribute("userList", list);
		PageUtil<List<User>> pageUtil = new PageUtil();
		pageUtil.setCode(0);
		pageUtil.setMsg("");
		pageUtil.setCount(list.size());
		pageUtil.setData(list);
		pageUtil.setJsonData(pageUtil);
		return pageUtil;
	}

	/**
	 * 根据id查询User
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "queryUserById", method = RequestMethod.GET)
	public User queryUserById(HttpServletRequest request, String id) {
		User user = new User();
		user.setId(id);
		user = services.queryUserById(user);
		return user;
	}

	/**
	 * 跳到修改页面（隐藏的给展示出来）
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "toUpdateUser", method = RequestMethod.GET)
	public String toUpdateUser(HttpServletRequest request, String id) {

		return "editUser";
	}

	/**
	 * 新增用户
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addUser", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, User user) {
		System.out.println("+++++++++++++++++++++++++++++++name:" + user.getName());
		services.addUser(user);
		return "success";
	}

	/**
	 * 修改form表单
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	public String update(HttpServletRequest request, User user) {
		System.out.println("修改name值为:" + user.getName());
		int a = services.updateUser(user);
		System.out.println("修改条数：" + a);
		return "success";
	}

	/**
	 * 删除
	 * 
	 * @param deleteList
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteUserList", method = RequestMethod.POST)
	private String deleteUserList(@RequestBody List<User> deleteList) {
		System.out.println(deleteList.size());
		services.deleteUser(deleteList);
		return "success";
	}
}
