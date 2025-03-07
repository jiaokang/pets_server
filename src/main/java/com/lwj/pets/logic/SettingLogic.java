package com.lwj.pets.logic;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.jwt.JWTUtil;
import com.lwj.pets.common.CommonConst;
import com.lwj.pets.common.ResultCode;
import com.lwj.pets.domain.BusinessOwners;
import com.lwj.pets.domain.BusinessSetting;
import com.lwj.pets.exception.BusinessException;
import com.lwj.pets.req.LoginByEmailParam;
import com.lwj.pets.req.LoginParam;
import com.lwj.pets.req.RegisterParam;
import com.lwj.pets.service.BusinessOwnersService;
import com.lwj.pets.service.BusinessSettingService;
import com.lwj.pets.service.EmailService;
import com.lwj.pets.utils.TokenUtils;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.lwj.pets.common.CommonConst.JWT_KEY;
import static com.lwj.pets.common.ResultCode.SYSTEM_ERROR;

@Slf4j
@Component
public class SettingLogic {


    @Autowired
    BusinessSettingService businessSettingService;


    /*
     * 获取用户设置项
     */
    public HashMap<String, Object> getSetting(String token) {
        Integer ownerId = TokenUtils.getOwnerId(token);
        HashMap<String, Object> map = new HashMap<>();
        businessSettingService.lambdaQuery().eq(BusinessSetting::getOwnerId, ownerId).list().forEach(item -> {
            map.put(item.getSettingCode(), item.getSettingValue());
        });
        return map;
    }

    /*
     * 设置用户设置项
     */
    public void setSetting(String token, String item) {
        Integer ownerId = TokenUtils.getOwnerId(token);
        BusinessSetting setting = businessSettingService.lambdaQuery().eq(BusinessSetting::getOwnerId, ownerId)
                .eq(BusinessSetting::getSettingCode, item).one();
        if ("true".equals(setting.getSettingValue())) {
            setting.setSettingValue("false");
        } else {
            setting.setSettingValue("true");
        }
        businessSettingService.updateById(setting);
    }
}
