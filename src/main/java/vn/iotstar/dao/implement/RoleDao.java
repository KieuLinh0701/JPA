package vn.iotstar.dao.implement;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.config.JPAConfig;
import vn.iotstar.dao.IRoleDao;
import vn.iotstar.entity.Role;

public class RoleDao implements IRoleDao{

	@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT count(c) FROM Role c";
		Query query = enma.createQuery(jpql);
		return ((Long)query.getSingleResult()).intValue();
	}

	@Override
	public List<Role> findAll(int page, int pagesize) {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Role> query = enma.createNamedQuery("Role.findAll", Role.class);
		query.setFirstResult(page*pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public List<Role> findByRoleName(String rolename) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT c FROM Role c WHERE c.roleName like : rolename";
		TypedQuery<Role> query = enma.createQuery(jpql, Role.class);
		query.setParameter("rolename", "%" + rolename + "%");
		return query.getResultList();
	}

	@Override
	public List<Role> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Role> query = enma.createNamedQuery("Role.findAll", Role.class);
		return query.getResultList();
	}

	@Override
	public Role findByRoleId(int roleId) {
		EntityManager enma = JPAConfig.getEntityManager();
		Role role = enma.find(Role.class, roleId);
		return role;
	}

	@Override
	public void delete(int roleId) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Role role = enma.find(Role.class, roleId);
			if (role != null) {
				enma.remove(role);
			}
			else {
				throw new Exception("Không tìm thấy");
			}
			enma.remove(role);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void update(Role role) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(role);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void insert(Role role) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(role);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

}
