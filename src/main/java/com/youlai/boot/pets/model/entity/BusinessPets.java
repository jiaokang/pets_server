package com.youlai.boot.pets.model.entity;

import lombok.Getter;
import lombok.Setter;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;

import java.time.LocalDate;

/**
 * 宠物信息实体对象
 *
 * @author lwj
 * @since 2025-02-24 21:57
 */
@Getter
@Setter
@TableName("business_pets")
public class BusinessPets extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 宠物名称
     */
    private String name;
    /**
     * 宠物种类（猫/狗/其他）
     */
    private String species;
    /**
     * 宠物品种
     */
    private String breed;
    /**
     * 宠物出生日期
     */
    private LocalDate birthDate;
    /**
     * 关联主人ID
     */
    private Integer ownerId;
    /**
     * 宠物照片路径
     */
    private String profileImagePath;
}
