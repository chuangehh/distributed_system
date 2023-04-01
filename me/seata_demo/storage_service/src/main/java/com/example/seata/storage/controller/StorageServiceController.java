package com.example.seata.storage.controller;

import com.example.seata.common.api.StorageWebApi;
import com.example.seata.common.dto.StorageDTO;
import com.example.seata.storage.entity.StorageModel;
import com.example.seata.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageServiceController implements StorageWebApi {

    @Autowired
    private StorageService service;

    @RequestMapping("/saveStorage")
    public String saveStorage(@RequestBody StorageDTO dto) {
        StorageModel sm = new StorageModel();
        sm.setUuid(dto.getUuid());
        sm.setProductId(dto.getProductId());
        sm.setNum(dto.getNum());
        service.saveStorage(sm);

        if ("666".equals(dto.getUuid())) {
            int i = 10 / 0;
        }
        return "now save storage===" + sm;
    }
}