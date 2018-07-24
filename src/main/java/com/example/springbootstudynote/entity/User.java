package com.example.springbootstudynote.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

/**
 * @Description：
 * @Auther： Administrator
 * @date： 2018/7/23:21:16
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    /**
     * SpringBoot表单验证@Valid是spring-data-jpa的功能,要先导入依赖
     */
    @Min(value = 18, message = "未成年禁止入内！")
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
