package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.User;

public interface IUserService {
	
	int count();

	List<User> findAll(int page, int pagesize);
	
	List<User> findByUsername(String username);

	List<User> findAll();

	User findById(int userId);

	void delete(int userId) throws Exception;

	void update(User user);

	void insert(User user);
}
