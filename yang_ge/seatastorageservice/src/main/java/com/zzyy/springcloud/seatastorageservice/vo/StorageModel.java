package com.zzyy.springcloud.seatastorageservice.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.zzyy.springcloud.seataservicecommon.storage.dto.StorageDTO;

@Entity
@Table(name="tbl_storage")
public class StorageModel extends StorageDTO{

}
