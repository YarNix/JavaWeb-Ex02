package vn.yarnix.services;

import vn.yarnix.models.UserModel;

public interface IUserService {
	UserModel login(String username, String password);
	
}
