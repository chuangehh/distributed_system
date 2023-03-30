package com.zzyy.springcloud.seataservicecommon.storage.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzyy.springcloud.seataservicecommon.storage.dto.StorageDTO;

@FeignClient(value = "seataStorageService")
public interface StorageWebApi {

    @RequestMapping("/saveStorage")
    public String saveStorage(@RequestBody StorageDTO dto);

}


