package vn.yarnix.services.impl;

import vn.yarnix.models.UserModel;
import vn.yarnix.services.IUserService;
import vn.yarnix.dao.IUserDao;
import vn.yarnix.dao.impl.UserDaoImpl;

public class UserService implements IUserService {
	IUserDao dao = new UserDaoImpl();
	
	@Override
	public UserModel login(String username, String password) {
		if (!username.isBlank())
		{
			UserModel user = dao.findUserByUserName(username);
			if (user != null && user.getPassWord().equals(password))
				return user;
		}
		return null;
	}

}
