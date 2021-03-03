package com.cxxy.demo.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "fenxiao")
public class FenXiao {
    private static final long serialVersionUID = 1L;

    @Id
    private int fx_id;

    private int fx_percent;//分销比,单位为%
}
