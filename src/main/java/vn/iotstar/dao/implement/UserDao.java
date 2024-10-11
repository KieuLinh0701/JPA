package vn.iotstar.dao.implement;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.config.JPAConfig;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.dao.IVideoDao;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.User;
import vn.iotstar.entity.Video;

public class UserDao implements IUserDao{

	@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT count(c) FROM User c";
		Query query = enma.createQuery(jpql);
		return ((Long)query.getSingleResult()).intValue();
	}

	@Override
	public List<User> findAll(int page, int pagesize) {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<User> query = enma.createNamedQuery("User.findAll", User.class);
		query.setFirstResult(page*pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public List<User> findByUsername(String userName) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT c FROM User c WHERE c.username like : userName";
		TypedQuery<User> query = enma.createQuery(jpql, User.class);
		query.setParameter("userName", "%" + userName + "%");
		return query.getResultList();
	}

	@Override
	public List<User> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<User> query = enma.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}

	@Override
	public void delete(int userId) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			User user = enma.find(User.class, userId);
			if (user != null) {
				enma.remove(user);
			}
			else {
				throw new Exception("Không tìm thấy");
			}
			enma.remove(user);
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
	public void update(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(user);
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
	public void insert(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(user);
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
	public User findById(int userId) {
		EntityManager enma = JPAConfig.getEntityManager();
		User user = enma.find(User.class, userId);
		return user;
	}
	
}
