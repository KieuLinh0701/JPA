package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.User;

public interface IUserDao {
	
	int count();

	List<User> findAll(int page, int pagesize);
	
	List<User> findByUsername(String username);

	List<User> findAll();

	User findById(int userId);

	void delete(int userId) throws Exception;

	void update(User user);

	void insert(User user);
}
