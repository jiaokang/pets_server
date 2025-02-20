package com.youlai.boot.owners.model.entity;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;

/**
 * 主人信息实体对象
 *
 * @author lwj
 * @since 2025-02-19 21:28
 */
@Getter
@Setter
@TableName("business_owners")
public class BusinessOwners extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主人姓名
     */
    private String name;
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

}
