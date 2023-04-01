package com.example.seata.order.controller;

import com.example.seata.order.entity.OrderModel;
import com.example.seata.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * test:	http://localhost:9001/saveOrder?uuid=1&orderId=1&totalMoney=1.1
 */
@RestController
public class OrderServiceController {

	@Autowired
	private OrderService service;

	@RequestMapping("/saveOrder")
	public String saveOrder( OrderModel om) {
		service.saveOrder(om);
		return "now save storage==="+om;
	}

}
