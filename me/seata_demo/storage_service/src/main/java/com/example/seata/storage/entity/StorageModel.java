package com.example.seata.storage.entity;


import com.example.seata.common.dto.StorageDTO;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tbl_storage")
public class StorageModel extends StorageDTO {

}
