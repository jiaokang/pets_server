package com.lwj.pets.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 设置项目
 * @TableName business_setting
 */
@TableName(value ="business_setting")
@Data
public class BusinessSetting {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private Integer ownerId;

    /**
     * 设置项名称
     */
    private String settingName;

    /**
     * 设置项编码
     */
    private String settingCode;

    /**
     * 设置项值
     */
    private String settingValue;
}