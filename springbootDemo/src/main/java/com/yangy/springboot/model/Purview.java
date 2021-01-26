package com.yangy.springboot.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "purview")
public class Purview {

    @Id
    @Column(name = "purview_id")
    private Integer purviewId;

    @Column(name = "purview_name")
    private String purviewName;
}
