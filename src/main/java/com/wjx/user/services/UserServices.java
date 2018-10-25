package com.wjx.user.services;

import java.util.List;

import com.wjx.user.pojo.User;

public interface UserServices {

	public List<User> queryUserList();

	public User queryUserById(User user);

	public int addUser(User user);

	public int updateUser(User user);

	public int deleteUser(List<User> users);

}
