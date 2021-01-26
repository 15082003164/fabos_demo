package com.yangy.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user_role")
@AllArgsConstructor
public class UserRole implements Serializable {

    @Id
    @Column(name = "id")
    private Integer userId;

    @Column(name = "role_id")
    private Integer roleId;

}
