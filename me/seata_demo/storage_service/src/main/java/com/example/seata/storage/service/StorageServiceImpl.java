package com.example.seata.storage.service;

import com.example.seata.storage.dao.StorageDAO;
import com.example.seata.storage.entity.StorageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageDAO dao;

    // RM
    @Override
    public String saveStorage(StorageModel sm) {
        dao.saveStorage(sm);
        return "okok";
    }

}
