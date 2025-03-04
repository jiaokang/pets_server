package com.lwj.pets.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 宠物驱虫记录表
 * @TableName business_deworming
 */
@TableName(value ="business_deworming")
@Data
public class BusinessDeworming {
    /**
     * 驱虫记录唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 关联宠物ID
     */
    private Integer petId;

    /**
     * 主人ID
     */
    private Integer ownerId;

    /**
     * 驱虫类型（体内/体外）
     */
    private String dewormType;

    /**
     * 驱虫药物名称
     */
    private String productName;

    /**
     * 剂量(mL)
     */
    private BigDecimal metering;

    /**
     * 体重(kg)
     */
    private BigDecimal weight;

    /**
     * 驱虫日期
     */
    private LocalDate dewormDate;

    /**
     * 下次驱虫日期
     */
    private LocalDate nextDate;

    /**
     * 驱虫地点
     */
    private String address;

    /**
     * 备注
     */
    private String notes;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}