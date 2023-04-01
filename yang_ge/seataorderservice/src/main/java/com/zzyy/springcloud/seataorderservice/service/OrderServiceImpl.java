package com.zzyy.springcloud.seataorderservice.service;

import com.zzyy.springcloud.seataorderservice.dao.OrderDAO;
import com.zzyy.springcloud.seataorderservice.vo.OrderModel;
import com.zzyy.springcloud.seataservicecommon.storage.api.StorageWebApi;
import com.zzyy.springcloud.seataservicecommon.storage.dto.StorageDTO;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDAO dao;

    @Autowired
    private StorageWebApi api;

    @GlobalTransactional
    public String saveOrder(OrderModel sm) //TM
    {
        //1 先处理order的数据
        dao.saveOrder(sm); //RM

        //2 再调用仓储服务，去处理库存的数据
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
