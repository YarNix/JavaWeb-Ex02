package vn.yarnix.dao;

import vn.yarnix.models.UserModel;

public interface IUserDao {
	
	UserModel findUserById(int id);
	UserModel findUserByUserName(String username);
	boolean insert(UserModel user);
}
