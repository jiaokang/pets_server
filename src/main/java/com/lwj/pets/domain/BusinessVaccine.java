package com.lwj.pets.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 宠物疫苗记录表
 * @TableName business_vaccine
 */
@TableName(value ="business_vaccine")
@Data
public class BusinessVaccine {
    /**
     * 疫苗记录唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 主人ID
     */
    private Integer ownerId;

    /**
     * 关联宠物ID
     */
    private Integer petId;

    /**
     * 疫苗类型（如狂犬病、猫三联）
     */
    private String vaccineName;

    /**
     * 接种日期
     */
    private LocalDate vaccineDate;

    /**
     * 下次接种日期
     */
    private LocalDate nextDate;

    /**
     * 接种医院名称
     */
    private String hospital;

    /**
     * 备注
     */
    private String notes;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}