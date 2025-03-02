package com.lwj.pets.logic;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.lwj.pets.common.ResultCode;
import com.lwj.pets.domain.BusinessOwners;
import com.lwj.pets.domain.BusinessPets;
import com.lwj.pets.exception.BusinessException;
import com.lwj.pets.req.AddPetParam;
import com.lwj.pets.req.LoginParam;
import com.lwj.pets.req.UpdatePetParam;
import com.lwj.pets.res.PetResult;
import com.lwj.pets.service.BusinessOwnersService;
import com.lwj.pets.service.BusinessPetsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lwj.pets.common.CommonConst.JWT_KEY;

@Slf4j
@Component
public class PetsLogic {

    @Autowired
    private BusinessPetsService businessPetsService;

    /**
     * 添加宠物
     */
    public void addPet(AddPetParam addPetParam,String token) {
        // 从token中获取主人ID
        JWT jwt = JWTUtil.parseToken(token);
        Integer ownerId = Integer.valueOf(jwt.getPayload("id").toString());
        BusinessPets businessPets = new BusinessPets();
        businessPets.setOwnerId(ownerId);
        businessPets.setName(addPetParam.getName());
        businessPets.setBreed(addPetParam.getBreed());
        businessPets.setAge(addPetParam.getAge());
        businessPetsService.save(businessPets);
    }

    /**
     * 宠物列表
     */
    public List<PetResult> petList(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        Integer ownerId = Integer.valueOf(jwt.getPayload("id").toString());
        List<BusinessPets> businessPets = businessPetsService.lambdaQuery().eq(BusinessPets::getOwnerId, ownerId).list();
        return businessPets.stream().map(businessPet -> {
            PetResult petResult = new PetResult();
            petResult.setId(businessPet.getId());
            petResult.setName(businessPet.getName());
            petResult.setBreed(businessPet.getBreed());
            petResult.setAge(businessPet.getAge());
            return petResult;
        }).toList();
    }

    /**
     * 删除宠物
     */
    public void deletePet(Integer petId, String token) {
        JWT jwt = JWTUtil.parseToken(token);
        Integer ownerId = Integer.valueOf(jwt.getPayload("id").toString());
        businessPetsService.lambdaUpdate().eq(BusinessPets::getOwnerId, ownerId).eq(BusinessPets::getId, petId).remove();
    }
    /**
     * 更新宠物
     */
    public void updatePet(UpdatePetParam updatePetParam, String token) {
        JWT jwt = JWTUtil.parseToken(token);
        Integer ownerId = Integer.valueOf(jwt.getPayload("id").toString());
        BusinessPets businessPets = businessPetsService.lambdaQuery().eq(BusinessPets::getOwnerId, ownerId).eq(BusinessPets::getId, updatePetParam.getId()).one();
        if (businessPets == null) {
            log.error("宠物不存在");
            throw new BusinessException(ResultCode.PET_NOT_FOUND);
        }
        businessPets.setName(updatePetParam.getName());
        businessPets.setBreed(updatePetParam.getBreed());
        businessPets.setAge(updatePetParam.getAge());
        businessPetsService.updateById(businessPets);
    }
}
