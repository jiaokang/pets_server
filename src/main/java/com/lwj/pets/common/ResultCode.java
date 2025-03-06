package com.lwj.pets.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应码枚举
 * <p>
 * 参考阿里巴巴开发手册响应码规范
 *
 * @author Ray.Hao
 * @since 2020/6/23
 **/
@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode implements IResultCode, Serializable {

    SUCCESS("00000", "一切ok"),


    USER_PASSWORD_ERROR("A0001", "用户名或密码错误"),
    EMAIL_CODE_ERROR("A0002", "邮箱验证码错误或已过期"),


    PET_NOT_FOUND("P0001", "宠物不存在"),

    VACCINE_NOT_FOUND("V0001", "接种记录不存在"),


    SYSTEM_ERROR("S0001", "系统执行出错"),
    ;


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    private String code;

    private String msg;

    @Override
    public String toString() {
        return "{" +
                "\"code\":\"" + code + '\"' +
                ", \"msg\":\"" + msg + '\"' +
                '}';
    }


    public static ResultCode getValue(String code) {
        for (ResultCode value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return SYSTEM_ERROR; // 默认系统执行错误
    }
}