package com.example.seata.common.api;

import com.example.seata.common.dto.StorageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "storageService")
public interface StorageWebApi {

    @RequestMapping("/saveStorage")
    public String saveStorage(@RequestBody StorageDTO dto);

}
