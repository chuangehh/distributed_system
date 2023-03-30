package com.zzyy.springcloud.seatastorageservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zzyy.springcloud.seataservicecommon.storage.api.StorageWebApi;
import com.zzyy.springcloud.seataservicecommon.storage.dto.StorageDTO;
import com.zzyy.springcloud.seatastorageservice.service.StorageService;
import com.zzyy.springcloud.seatastorageservice.vo.StorageModel;

@RestController
public class StorageServiceController implements StorageWebApi
{
	@Autowired
	private StorageService service;

	@RequestMapping("/saveStorage")
	public String saveStorage(@RequestBody StorageDTO dto)
	{
		StorageModel sm = new StorageModel();
		
		sm.setUuid(dto.getUuid());
		sm.setProductId(dto.getProductId());
		sm.setNum(dto.getNum());

		service.saveStorage(sm);
		
		return "now save storage==="+sm;
	}
}



/*@RestController
public class StorageServiceController{
	@Autowired
	private StorageService service;

	@RequestMapping("/saveStorage")
	public String saveStorage(StorageDTO dto)
	{
		StorageModel sm = new StorageModel();

		sm.setUuid(dto.getUuid());
		sm.setProductId(dto.getProductId());
		sm.setNum(dto.getNum());

		service.saveStorage(sm);

		return "now save storage==="+sm;
	}
}*/
