package vn.yarnix.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import vn.yarnix.configs.DBConnectSQL;
import vn.yarnix.dao.IUserDao;
import vn.yarnix.models.UserModel;

public class UserDao implements IUserDao {
	
	private UserModel resultToUserModel(ResultSet rs) throws SQLException
	{
		if (rs.next()) 
			return new UserModel(rs.getInt("id"), rs.getString("email"), rs.getString("userName"), rs.getString("fullName"), rs.getString("passWord"), rs.getString("avatar"), rs.getInt("roleid"), rs.getString("phone"), rs.getTimestamp("createdDate").toLocalDateTime());
		else
			return null;
	}
	
	@Override
	public UserModel findUserById(int id) {
		try {
			Connection conn = DBConnectSQL.getConnection();
			PreparedStatement st = conn.prepareStatement("SELECT * FROM Users WHERE id = ?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			return resultToUserModel(rs);
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public UserModel findUserByUserName(String username) {
		try {
			Connection conn = DBConnectSQL.getConnection();
			PreparedStatement st = conn.prepareStatement("SELECT * FROM Users WHERE userName = ?");
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			
			return resultToUserModel(rs);
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public UserModel findUserByEmail(String email) {
		try {
			Connection conn = DBConnectSQL.getConnection();
			PreparedStatement st = conn.prepareStatement("SELECT * FROM Users WHERE email = ?");
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			
			return resultToUserModel(rs);
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
		    st.setTimestamp(8, Timestamp.valueOf(user.getCreatedDate()));
		    int rows = st.executeUpdate();
		    return rows > 0;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void setPassword(int userId, String password) {
		try {
			Connection conn = DBConnectSQL.getConnection();
			PreparedStatement st = conn.prepareStatement("UPDATE Users SET passWord = ? WHERE id = ?");
			st.setString(1, password);
			st.setInt(2, userId);
			st.executeUpdate();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Override
	public int findRoleByName(String name) {
		try {
			Connection conn = DBConnectSQL.getConnection();
			PreparedStatement st = conn.prepareStatement("SELECT * FROM Roles WHERE rolename = ?");
			st.setString(1, name);
			ResultSet rs = st.executeQuery();
			
			if (rs.next())
				return rs.getInt("roleid");
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}

	@Override
	public int getDefaultRoleId() {
		return findRoleByName("USER");
	}

	@Override
	public boolean isRole(int id, String string) {
		try {
			Connection conn = DBConnectSQL.getConnection();
			PreparedStatement st = conn.prepareStatement("SELECT * FROM Users JOIN Roles ON Users.roleid = Roles.roleid WHERE Users.id = ? AND rolename = ?");
			st.setInt(1, id);
			st.setString(2, string);
			ResultSet rs = st.executeQuery();
			
			return rs.next();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public int getUserIdByResetToken(String token) {
		String sql = "SELECT userId FROM PasswordReset WHERE token = ?";
        try {
        	Connection conn = DBConnectSQL.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, token);
            ResultSet rs = st.executeQuery();
            if (rs.next())
            	return rs.getInt("userId");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // not found
	}

	@Override
	public boolean isResetTokenValid(String token) {
		String sql = "SELECT TOP 1 * FROM PasswordReset WHERE userId = ? AND expiry > GETDATE() ORDER BY createdAt DESC;";

        try {
        	Connection conn = DBConnectSQL.getConnection();
        	PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, token);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public void saveResetToken(int userId, String token, LocalDateTime expiry) {
		String sql = "INSERT INTO PasswordReset (userId, token, expiry) VALUES (?, ?, ?)";

        try {
        	Connection conn = DBConnectSQL.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userId);
            st.setString(2, token);
            st.setTimestamp(3, Timestamp.valueOf(expiry));

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void deleteResetToken(int userId) {
		String sql = "DELETE FROM PasswordReset WHERE userId = ?";
		try {
			Connection conn = DBConnectSQL.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userId);
            st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
