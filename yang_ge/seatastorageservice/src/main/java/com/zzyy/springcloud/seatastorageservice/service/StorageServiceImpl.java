package com.zzyy.springcloud.seatastorageservice.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzyy.springcloud.seatastorageservice.dao.StorageDAO;
import com.zzyy.springcloud.seatastorageservice.vo.StorageModel;

@Service
@Transactional
public class StorageServiceImpl implements StorageService{
	@Autowired
	private StorageDAO dao;
	
	@Override
	//RM
	public String saveStorage(StorageModel sm) {
		dao.saveStorage(sm);
		return "okok";
	}

}
