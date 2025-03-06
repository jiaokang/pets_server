package com.lwj.pets.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 主人信息表
 * @TableName business_owners
 */
@Builder
@Accessors(chain = true)
@TableName(value ="business_owners")
@Data
public class BusinessOwners {
    /**
     * 主人唯一标识
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String name;
    /**
     * 昵称
     */
    private String nickName;

    /**
     * 主人手机号
     */
    private String phone;

    /**
     * 主人邮箱
     */
    private String email;

    /**
     * 主人地址
     */
    private String address;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 密码
     */
    private String pwd;
}