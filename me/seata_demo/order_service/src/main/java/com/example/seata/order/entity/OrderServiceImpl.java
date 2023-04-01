package com.example.seata.order.entity;

import com.example.seata.common.api.StorageWebApi;
import com.example.seata.common.dto.StorageDTO;
import com.example.seata.order.dao.OrderDAO;
import com.example.seata.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO dao;

    @Autowired
    private StorageWebApi api;

    // TM 事务发起者，找到seata，搞一个全局事务id
    @Transactional
    @GlobalTransactional
    public String saveOrder(OrderModel sm) {
        // RM1 先处理order的数据
        dao.saveOrder(sm);

        // RM2 再调用仓储服务，去处理库存的数据
        StorageDTO dto = new StorageDTO();
        dto.setUuid(sm.getUuid());
        dto.setProductId(sm.getUuid());
        dto.setNum(new Random().nextInt(5) + 1);

        api.saveStorage(dto);

        //3 回滚测试判断
        if (sm.getTotalMoney() == 4444) {
            int a = 5 / 0;
        }
        return "ok---->" + sm;
    }

}
