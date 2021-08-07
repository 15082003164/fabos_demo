package com.yangy.practice.Model;

/**
 * @RequiredArgsConstructor和 @NoArgsConstructor不能同时存在
 * 字段必须为final修饰，并且未被初始化
 * 有@NonNull注解修饰
 */

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class User {

    private int id;

    private String name;

    private Long tel;

    @NonNull
    private final String classname;

}
