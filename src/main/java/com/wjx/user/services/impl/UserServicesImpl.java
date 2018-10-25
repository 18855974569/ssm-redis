package com.wjx.user.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.wjx.user.mapper.UserMapper;
import com.wjx.user.pojo.User;
import com.wjx.user.services.UserServices;

@Service
public class UserServicesImpl implements UserServices {

	@Autowired
	private UserMapper userMapper;
	

	public List<User> queryUserList() {
		return userMapper.queryUserList();
	}

	public User queryUserById(User user) {
		return userMapper.queryUserById(user);
	}

	public int addUser(User user) {
		return userMapper.addUser(user);
	}

	public int updateUser(User user) {
		return userMapper.updateUser(user);
	}

	@Transactional
	public int deleteUser(List<User> users) {
		int len = 0;
		try {
			for (User user : users) {
				userMapper.deleteUser(user);
				len++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
		}
		return len;
	}

}
