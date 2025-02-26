package com.lwj.pets.logic;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.jwt.JWTUtil;
import com.lwj.pets.common.ResultCode;
import com.lwj.pets.domain.BusinessOwners;
import com.lwj.pets.exception.BusinessException;
import com.lwj.pets.req.LoginParam;
import com.lwj.pets.service.BusinessOwnersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.lwj.pets.common.CommonConst.JWT_KEY;

@Slf4j
@Component
public class AuthLogic {

    @Autowired
    private BusinessOwnersService businessOwnersService;

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
}
