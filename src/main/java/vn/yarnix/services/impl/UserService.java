package vn.yarnix.services.impl;

import vn.yarnix.models.UserModel;
import vn.yarnix.services.IUserService;

import java.util.Date;

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

	@Override
	public boolean register(String email, String password, String username, String fullname, String phone) {
		UserModel user = dao.findUserByUserName(username);
		if (user != null)
			return false;
		
		user = new UserModel(0, email, username, fullname, password, null, dao.getDefaultRoleId(), phone, new Date());
		return dao.insert(user);
	}

	@Override
	public boolean checkExistUsername(String username) {
		UserModel user = dao.findUserByUserName(username);
		return user != null;
	}

}
