package vn.yarnix.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.yarnix.configs.DBConnectSQL;
import vn.yarnix.dao.ICategoryDao;
import vn.yarnix.models.CategoryModel;

public class CategoryDao implements ICategoryDao {

	@Override
	public List<CategoryModel> findAll() {
		List<CategoryModel> list = new ArrayList<CategoryModel>();
		String sql = "SELECT * FROM Category";

		try {
			Connection conn = DBConnectSQL.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CategoryModel c = new CategoryModel();
				c.setId(rs.getInt("id"));
				c.setCategoryName(rs.getString("category_name"));
				c.setIcon(rs.getString("icon"));
				c.setStatus(rs.getInt("status"));
				list.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public CategoryModel findById(int id) {
		String sql = "SELECT * FROM Category WHERE id = ?";
		try (Connection conn = DBConnectSQL.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					CategoryModel c = new CategoryModel();
					c.setId(rs.getInt("id"));
					c.setCategoryName(rs.getString("category_name"));
					c.setIcon(rs.getString("icon"));
					c.setStatus(rs.getInt("status"));
					return c;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CategoryModel> findCategoryByName(String namePattern) {
		List<CategoryModel> list = new ArrayList<>();
		String sql = "SELECT * FROM Category WHERE category_name LIKE ?";

		try (Connection conn = DBConnectSQL.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, "%" + namePattern + "%");
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					CategoryModel c = new CategoryModel();
					c.setId(rs.getInt("id"));
					c.setCategoryName(rs.getString("category_name"));
					c.setIcon(rs.getString("icon"));
					c.setStatus(rs.getInt("status"));
					list.add(c);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void insert(CategoryModel category) {
		String sql = "INSERT INTO Category (category_name, icon, status) VALUES (?, ?, ?)";

		try (Connection conn = DBConnectSQL.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, category.getCategoryName());
			ps.setString(2, category.getIcon());
			ps.setInt(3, category.getStatus());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(CategoryModel category) {
		String sql = "UPDATE Category SET category_name = ?, icon = ?, status = ? WHERE id = ?";

		try (Connection conn = DBConnectSQL.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, category.getCategoryName());
			ps.setString(2, category.getIcon());
			ps.setInt(3, category.getStatus());
			ps.setInt(4, category.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM Category WHERE id = ?";

		try (Connection conn = DBConnectSQL.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
