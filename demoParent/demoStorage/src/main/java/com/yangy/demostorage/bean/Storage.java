package com.yangy.demostorage.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "storage")
public class Storage {

    //物品id
    @Id
    @Column(name = "storage_id")
    private String storageId;

    //物品名称
    @Column(name = "stuff_name")
    private String stuffName;

    //物品库存量
    @Column(name = "storage_quantity")
    private Integer storageQuantity;

}
