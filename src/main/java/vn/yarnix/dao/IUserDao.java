package vn.yarnix.dao;

import java.time.LocalDateTime;

import vn.yarnix.models.UserModel;

public interface IUserDao {
	
	UserModel findUserById(int id);
	UserModel findUserByUserName(String username);
	UserModel findUserByEmail(String email);
	boolean insert(UserModel user);
	void setPassword(int userId, String password);
	int findRoleByName(String name);
	int getDefaultRoleId();
	
	int getUserIdByResetToken(String token);
	boolean isResetTokenValid(String token);
	void saveResetToken(int userId, String token, LocalDateTime expiry);
	void deleteResetToken(int userId);
}
