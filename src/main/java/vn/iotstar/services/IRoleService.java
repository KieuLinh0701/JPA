package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.Role;

public interface IRoleService {
	int count();

	List<Role> findAll(int page, int pagesize);

	List<Role> findByRoleName(String roleName);

	List<Role> findAll();

	Role findByRoleId(int roleId);

	void delete(int roleId) throws Exception;

	void update(Role role);

	void insert(Role role);
}
