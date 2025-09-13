package vn.yarnix.dao;

import java.util.List;
import vn.yarnix.models.CategoryModel;

public interface ICategoryDao {
	List<CategoryModel> findAll();
	CategoryModel findById(int id);
	List<CategoryModel> findCategoryByName(String namePattern);
	
	void insert(CategoryModel category);
	void update(CategoryModel category);
	void delete(int id);
}
