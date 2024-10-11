package vn.iotstar.services.implement;

import java.util.List;

import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.dao.IRoleDao;
import vn.iotstar.dao.implement.CategoryDao;
import vn.iotstar.dao.implement.RoleDao;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Role;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.IRoleService;

public class RoleService implements IRoleService{
	IRoleDao roleDao = new RoleDao();
	
	@Override
	public int count() {
		return roleDao.count();
	}

	@Override
	public List<Role> findAll(int page, int pagesize) {
		return roleDao.findAll(page, pagesize);
	}

	@Override
	public List<Role> findByRoleName(String roleName) {
		return roleDao.findByRoleName(roleName);
	}

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public Role findByRoleId(int roleId) {
		return roleDao.findByRoleId(roleId);
	}

	@Override
	public void delete(int roleId) throws Exception {
		roleDao.delete(roleId);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

	@Override
	public void insert(Role role) {
		roleDao.insert(role);
	}
}
