package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.Category;

public interface ICategoryService {
	int count();

	List<Category> findAll(int page, int pagesize);

	List<Category> findByCategoryName(String cateName);

	List<Category> findAll();

	Category findById(int cateId);

	void delete(int cateId) throws Exception;

	void update(Category category);

	void insert(Category category);
}
