package com.lwj.pets.logic;

import cn.hutool.core.util.IdUtil;
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
import com.lwj.pets.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lwj.pets.common.CommonConst.JWT_KEY;

@Slf4j
@Component
public class PetsLogic {

    @Autowired
    private BusinessPetsService businessPetsService;

    @Value("${profile-path}")
    private String profilePath;

    /**
     * 添加宠物
     */
    public void addPet(AddPetParam addPetParam, String token) {
        // 从token中获取主人ID
        JWT jwt = JWTUtil.parseToken(token);
        Integer ownerId = Integer.valueOf(jwt.getPayload("id").toString());
        BusinessPets businessPets = new BusinessPets();
        businessPets.setOwnerId(ownerId);
        businessPets.setName(addPetParam.getName());
        businessPets.setBreed(addPetParam.getBreed());
        businessPets.setAge(addPetParam.getAge());
        businessPets.setSex(addPetParam.getSex());
        businessPets.setProfileImagePath(addPetParam.getAvatar());
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
            petResult.setSex(businessPet.getSex());
            petResult.setAvatar(businessPet.getProfileImagePath());
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
        businessPets.setSex(updatePetParam.getSex());
        businessPetsService.updateById(businessPets);
    }

    /**
     * 上传头像
     */
    public String uploadAvatar(MultipartFile file, String token) {
        try {
            TokenUtils.getOwnerId(token);
        } catch (Exception e) {
            log.error("token异常");
            throw new BusinessException(ResultCode.TOKEN_ERROR);
        }
        String profileCode = IdUtil.fastSimpleUUID();
        String fileName = profileCode + ".jpg";
        Path path = Paths.get(profilePath, fileName);
        try {
            file.transferTo(path);
        } catch (IOException e) {
            log.error("文件上传失败");
            throw new BusinessException(ResultCode.SYSTEM_ERROR);
        }
        return profileCode;
    }

    /*
     * 获取头像
     */
    public ResponseEntity<Resource> getAvatar(String avatarCode) {
        try {
            Path path = Paths.get(profilePath, avatarCode + ".jpg");
            Resource resource = new UrlResource(path.toUri());

            if (resource.exists() || resource.isReadable()) {
                MediaType mediaType = MediaTypeFactory.getMediaType(resource)
                        .orElse(MediaType.APPLICATION_OCTET_STREAM);
                return ResponseEntity.ok()
                        .contentType(mediaType)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("文件不存在或不可读");
            }
        } catch (Exception e) {
            log.error("文件获取失败", e);
            throw new BusinessException(ResultCode.SYSTEM_ERROR);
        }
    }
}
