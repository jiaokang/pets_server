package com.lwj.pets.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 宠物信息表
 * @TableName business_pets
 */
@TableName(value ="business_pets")
@Data
public class BusinessPets {
    /**
     * 宠物唯一标识
     */
    @TableId(type = IdType.AUTO)
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
     * 体重
     */
    private Float weight;

    /**
     * 关联主人ID
     */
    private Integer ownerId;

    /**
     * 宠物照片路径
     */
    private String profileImagePath;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}