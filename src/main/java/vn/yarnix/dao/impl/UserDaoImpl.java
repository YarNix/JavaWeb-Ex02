package vn.yarnix.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vn.yarnix.configs.DBConnectSQL;
import vn.yarnix.dao.IUserDao;
import vn.yarnix.models.UserModel;

public class UserDaoImpl implements IUserDao {
	
	@Override
	public UserModel findUserById(int id) {
		try {
			Connection conn = DBConnectSQL.getConnection();
			PreparedStatement st = conn.prepareStatement("SELECT * FROM Users WHERE id = ?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				return new UserModel(rs.getInt("id"), rs.getString("email"), rs.getString("userName"), rs.getString("fullName"), rs.getString("passWord"), rs.getString("avatar"), rs.getInt("roleid"), rs.getString("phone"), rs.getDate("createdDate"));
			}
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insert(UserModel user) {
		String sql = "INSERT INTO Users (email, userName, fullName, passWord, avatar, roleid, phone, createdDate) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			Connection conn = DBConnectSQL.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, user.getEmail());
		    st.setString(2, user.getUserName());
		    st.setString(3, user.getFullName());
		    st.setString(4, user.getPassWord());
		    st.setString(5, user.getAvatar());
		    st.setInt(6, user.getRoleid());
		    st.setString(7, user.getPhone());
		    st.setDate(8, new java.sql.Date(user.getCreatedDate().getTime()));
		    int rows = st.executeUpdate();
		    return rows > 0;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public UserModel findUserByUserName(String username) {
		try {
			Connection conn = DBConnectSQL.getConnection();
			PreparedStatement st = conn.prepareStatement("SELECT * FROM Users WHERE userName = ?");
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				return new UserModel(rs.getInt("id"), rs.getString("email"), rs.getString("userName"), rs.getString("fullName"), rs.getString("passWord"), rs.getString("avatar"), rs.getInt("roleid"), rs.getString("phone"), rs.getDate("createdDate"));
			}
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public int getDefaultRoleId() {
		try {
			Connection conn = DBConnectSQL.getConnection();
			PreparedStatement st = conn.prepareStatement("SELECT * FROM Roles WHERE rolename = ?");
			st.setString(1, "USER");
			ResultSet rs = st.executeQuery();
			
			if (rs.next())
				return rs.getInt("roleid");
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
			
		}
		return 1;
	}
}
