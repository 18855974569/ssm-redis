package com.wjx.user.mapper;

import java.util.List;

import com.wjx.user.pojo.User;

public interface UserMapper {
	
	public List<User> queryUserList();

	public User queryUserById(User User);

	public int addUser(User User);

	public int updateUser(User User);

	public int deleteUser(User User);
}
