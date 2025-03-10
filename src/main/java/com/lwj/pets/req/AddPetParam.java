package com.lwj.pets.req;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddPetParam {



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

    private String avatar;
}
