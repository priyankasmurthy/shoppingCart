package com.learning.spring.Service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.spring.Dao.InventoryDao;
import com.learning.spring.Model.Inventory;

@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private SessionFactory sessionFactory;
	private Session session;

	@Autowired
	private InventoryDao inventoryDao;

	@Transactional
	public void addProduct(Inventory item) {
		inventoryDao.addProduct(item);
	}

	@Transactional
	public void updateProduct(Inventory item) {
		inventoryDao.updateProduct(item);
	}

	@Transactional
	public Inventory getProduct(int inventoryId) {
		return inventoryDao.getProduct(inventoryId);
	}

	@Transactional
	public List getAllProducts() {
		return inventoryDao.getAllProducts();
	}

	@Override
	@Transactional
	public void updateCartField(int inventoryId, String isInCart) {
		Transaction trans = sessionFactory.getCurrentSession()
				.beginTransaction();
		session = sessionFactory.openSession();
		int query = session
				.createQuery(
						"UPDATE Inventory set isInCart = :isInCart"
								+ " where inventoryId = :inventoryId")
				.setParameter("inventoryId", inventoryId)
				.setParameter("isInCart", isInCart).executeUpdate();
		trans.commit();
	}

	@Override
	public List getAllProductsInCart() {
		Transaction trans = sessionFactory.getCurrentSession()
				.beginTransaction();
		session = sessionFactory.openSession();
		return session.createCriteria(Inventory.class)
				.add(Restrictions.eq("isInCart", "YES"))
				.add(Restrictions.ne("status", "RETAIL"))
				.addOrder(Order.asc("productName")).list();
	}

	@Override
	public void purchaseProduct(int inventoryId, String status) {
		int query = session
				.createQuery(
						"UPDATE Inventory set status = :status"
								+ " where inventoryId = :inventoryId")
				.setParameter("inventoryId", inventoryId)
				.setParameter("status", status).executeUpdate();
	}
}
