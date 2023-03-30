package com.zzyy.springcloud.seataorderservice.dao;

import com.zzyy.springcloud.seataorderservice.vo.OrderModel;

public interface OrderDAO {
	public String saveOrder(OrderModel sm);
}
