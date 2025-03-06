package com.lwj.pets.logic;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.jwt.JWTUtil;
import com.lwj.pets.common.CommonConst;
import com.lwj.pets.common.ResultCode;
import com.lwj.pets.domain.BusinessOwners;
import com.lwj.pets.exception.BusinessException;
import com.lwj.pets.req.LoginByEmailParam;
import com.lwj.pets.req.LoginParam;
import com.lwj.pets.req.RegisterParam;
import com.lwj.pets.service.BusinessOwnersService;
import com.lwj.pets.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.lwj.pets.common.CommonConst.JWT_KEY;
import static com.lwj.pets.common.ResultCode.SYSTEM_ERROR;

@Slf4j
@Component
public class AuthLogic {

    @Autowired
    private BusinessOwnersService businessOwnersService;
    @Autowired
    EmailService emailService;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /*
     * 注册
     */
    public void register(RegisterParam registerParam) {
        verifyCode(registerParam.getEmail(), registerParam.getCode());
        BusinessOwners businessOwners = BusinessOwners.builder()
                .name(registerParam.getUsername())
                .nickName(registerParam.getUsername())
                .pwd(SecureUtil.md5(registerParam.getPassword()))
                .email(registerParam.getEmail())
                .build();
        businessOwnersService.save(businessOwners);
    }


    /*
     * 账号密码登录
     */
    public HashMap<String, Object> login(LoginParam loginParam) {
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        boolean remember = loginParam.isRemember();
        BusinessOwners dbOwner = businessOwnersService.lambdaQuery().eq(BusinessOwners::getName, username).one();
        if (dbOwner == null) {
            log.error("用户不存在");
            throw new BusinessException(ResultCode.USER_PASSWORD_ERROR);
        }
        String md5Pwd = SecureUtil.md5(password);
        if (!dbOwner.getPwd().equals(md5Pwd)) {
            log.error("密码错误");
            throw new BusinessException(ResultCode.USER_PASSWORD_ERROR);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", getToken(dbOwner));
        map.put("name", dbOwner.getName());
        map.put("nickName", dbOwner.getNickName());
        return map;
    }


    private String getToken(BusinessOwners owner) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", owner.getId());
        return JWTUtil.createToken(map, JWT_KEY.getBytes());
    }


    /*
     * 获取邮箱验证码
     */
    public void getEmailVerifyCode(String email) {
        String code = RandomUtil.randomString(5);
        try {
            emailService.sendVerificationEmail(email, code, 5);
            redisTemplate.opsForValue().set(CommonConst.EMAIL_CODE_REDIS_PREFIX + email, code, 5, TimeUnit.MINUTES);
        } catch (MessagingException e) {
            log.error("发送邮件失败", e);
            throw new BusinessException(SYSTEM_ERROR);
        }
    }


    /*
     * 邮箱登录
     */
    public HashMap<String, Object> loginByEmail(LoginByEmailParam loginByEmailParam) {
        String email = loginByEmailParam.getEmail();
        String code = loginByEmailParam.getCode();
        verifyCode(email, code);
        BusinessOwners businessOwners = businessOwnersService.lambdaQuery().eq(BusinessOwners::getEmail, email).one();
        if (businessOwners == null) {
            log.error("用户不存在");
            throw new BusinessException(ResultCode.USER_PASSWORD_ERROR);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", getToken(businessOwners));
        map.put("name", businessOwners.getName());
        map.put("nickName", businessOwners.getNickName());
        return map;
    }

    private void verifyCode(String email, String code) {
        String redisCode = (String) redisTemplate.opsForValue().get(CommonConst.EMAIL_CODE_REDIS_PREFIX + email);
        if (redisCode == null || !redisCode.equals(code)) {
            log.error("验证码错误或已过期");
            throw new BusinessException(ResultCode.EMAIL_CODE_ERROR);
        }
    }


}
