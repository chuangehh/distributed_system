package com.zzyy.springcloud.seataorderservice.dao;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zzyy.springcloud.seataorderservice.vo.OrderModel;

@Repository
public class OrderDAOImpl implements OrderDAO{
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	public Session getH5Session() {
		Session s = null;
		try {
			s = entityManagerFactory.unwrap(SessionFactory.class).getCurrentSession();
		}catch(Exception err) {
			s = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		}
		return s;
	}
	
	@Override
	public String saveOrder(OrderModel sm) {
		this.getH5Session().save(sm);
		return "DAO save order is ok.";
	}

}
