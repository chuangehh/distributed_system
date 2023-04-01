package com.example.seata.order.dao;


import com.example.seata.order.entity.OrderModel;

public interface OrderDAO {
	public String saveOrder(OrderModel sm);
}
