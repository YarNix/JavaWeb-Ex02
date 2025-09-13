package vn.yarnix.models;

import java.io.Serializable;

public class CategoryModel implements Serializable {
	private static final long serialVersionUID = 8856210235342694603L;
	
	private int id;
	private String categoryName;
	private String icon;
	private int status;
	
	public CategoryModel() {
		super();
	}

	public CategoryModel(int id, String categoryName, String icon, int status) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.icon = icon;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
