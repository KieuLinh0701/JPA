package vn.iotstar.services.implement;

import java.util.List;

import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.dao.implement.CategoryDao;
import vn.iotstar.entity.Category;
import vn.iotstar.services.ICategoryService;

public class CategoryService implements ICategoryService{
	ICategoryDao cateDao = new CategoryDao();
	
	@Override
	public int count() {
		return cateDao.count();
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		return cateDao.findAll(page, pagesize);
	}

	@Override
	public List<Category> findByCategoryName(String cateName) {
		return cateDao.findByCategoryName(cateName);
	}

	@Override
	public List<Category> findAll() {
		return cateDao.findAll();
	}

	@Override
	public Category findById(int cateId) {
		return cateDao.findById(cateId);
	}

	@Override
	public void delete(int cateId) throws Exception {
		cateDao.delete(cateId);
	}

	@Override
	public void update(Category category) {
		cateDao.update(category);
	}

	@Override
	public void insert(Category category) {
		cateDao.insert(category);
	}
	
}
