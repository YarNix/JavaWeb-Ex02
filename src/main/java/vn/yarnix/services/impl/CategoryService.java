package vn.yarnix.services.impl;

import java.util.List;

import vn.yarnix.dao.ICategoryDao;
import vn.yarnix.dao.impl.CategoryDao;
import vn.yarnix.models.CategoryModel;
import vn.yarnix.services.ICategoryService;

public class CategoryService implements ICategoryService {
	ICategoryDao dao = new CategoryDao();


    @Override
    public void insert(CategoryModel category) {
        if (category != null) {
            dao.insert(category);
        } else {
            throw new IllegalArgumentException("Category cannot be null");
        }
    }

    @Override
    public void edit(CategoryModel category) {
        if (category != null && category.getId() > 0) {
            dao.update(category);
        } else {
            throw new IllegalArgumentException("Category is invalid for update");
        }
    }

    @Override
    public void delete(int id) {
        if (id > 0) {
            dao.delete(id);
        } else {
            throw new IllegalArgumentException("Invalid category id");
        }
    }

    @Override
    public CategoryModel get(int id) {
        if (id > 0) {
            return dao.findById(id);
        }
        return null;
    }

    @Override
    public CategoryModel get(String name) {
        if (name != null && !name.isEmpty()) {
            List<CategoryModel> list = dao.findCategoryByName(name);
            return list.isEmpty() ? null : list.get(0); // return first match
        }
        return null;
    }

    @Override
    public List<CategoryModel> getAll() {
        return dao.findAll();
    }

    @Override
    public List<CategoryModel> search(String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return dao.findCategoryByName(keyword);
        }
        return dao.findAll();
    }
}
