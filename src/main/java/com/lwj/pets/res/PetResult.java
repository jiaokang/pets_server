package com.lwj.pets.res;


import lombok.Data;

@Data
public class PetResult {

    private Integer id;

    /**
     * 宠物名称
     */
    private String name;

    /**
     * 宠物品种
     */
    private String breed;

    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别
     */
    private Integer sex;
}
