package com.example.seata.storage.service;

import com.example.seata.storage.dao.StorageDAO;
import com.example.seata.storage.entity.StorageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageDAO dao;

    // RM
    @Override
    @Transactional
    public String saveStorage(StorageModel sm) {
        dao.saveStorage(sm);
        return "okok";
    }

}
