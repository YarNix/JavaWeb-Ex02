package vn.yarnix.services;

import vn.yarnix.models.UserModel;

public interface IUserService {
	UserModel login(String username, String password);
	boolean register(String email, String password, String username, String fullname, String phone);
	boolean checkExistUsername(String username);
}
