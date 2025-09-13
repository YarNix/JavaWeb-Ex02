package vn.yarnix.services.impl;

import vn.yarnix.models.UserModel;
import vn.yarnix.services.IUserService;

import java.time.LocalDateTime;
import java.util.UUID;

import vn.yarnix.dao.IUserDao;
import vn.yarnix.dao.impl.UserDao;

public class UserService implements IUserService {
	IUserDao dao = new UserDao();
	
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
		
		user = new UserModel(0, email, username, fullname, password, null, dao.getDefaultRoleId(), phone, LocalDateTime.now());
		return dao.insert(user);
	}

	@Override
	public boolean checkExistUsername(String username) {
		UserModel user = dao.findUserByUserName(username);
		return user != null;
	}

	@Override
	public String generatePasswordResetToken(String email) {
		UserModel user = dao.findUserByEmail(email);
		if (user == null)
			return "";
		String token = UUID.randomUUID().toString();
		dao.saveResetToken(user.getId(), token, LocalDateTime.now().plusMinutes(15));
		return token;
	}

	@Override
	public void resetPassword(String resetToken, String password) {
		int userId = dao.getUserIdByResetToken(resetToken);
		if (userId < 0)
			return;
		dao.deleteResetToken(userId);
		dao.setPassword(userId, password);
	}

	@Override
	public boolean isRole(int id, String string) {
		return dao.isRole(id, string);
	}

}
