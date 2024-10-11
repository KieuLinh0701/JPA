package vn.iotstar.services.implement;

import java.util.List;

import vn.iotstar.dao.IUserDao;
import vn.iotstar.dao.implement.UserDao;
import vn.iotstar.entity.User;
import vn.iotstar.services.IUserService;

public class UserService implements IUserService{

	IUserDao userDao = new UserDao();
	@Override
	public int count() {
		return userDao.count();
	}

	@Override
	public List<User> findAll(int page, int pagesize) {
		return userDao.findAll(page, pagesize);
	}

	@Override
	public List<User> findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User findById(int userId) {
		return userDao.findById(userId);
	}

	@Override
	public void delete(int userId) throws Exception {
		userDao.delete(userId);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void insert(User user) {
		userDao.insert(user);
	}

}
