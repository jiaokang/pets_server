package com.lwj.pets.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwj.pets.domain.BusinessOwners;
import com.lwj.pets.domain.BusinessPets;
import com.lwj.pets.domain.BusinessVaccine;
import com.lwj.pets.service.BusinessOwnersService;
import com.lwj.pets.service.BusinessPetsService;
import com.lwj.pets.service.BusinessVaccineService;
import com.lwj.pets.mapper.BusinessVaccineMapper;
import com.lwj.pets.tasks.EmailNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
* @author 81170
* @description 针对表【business_vaccine(宠物疫苗记录表)】的数据库操作Service实现
* @createDate 2025-03-02 21:21:55
*/
@Service
public class BusinessVaccineServiceImpl extends ServiceImpl<BusinessVaccineMapper, BusinessVaccine>
    implements BusinessVaccineService{

    @Autowired
    private BusinessOwnersService businessOwnersService;
    @Autowired
    private BusinessPetsService businessPetsService;

    @Override
    public List<BusinessVaccine> getVaccineRecordByOwnerId(Integer ownerId) {
        return this.lambdaQuery().eq(BusinessVaccine::getOwnerId,ownerId).list();
    }

    @Override
    public List<EmailNotify> getVaccineNotifyByOwnerId(Integer ownerId) {
        // 获取所有相关的BusinessVaccine记录
        List<BusinessVaccine> businessVaccines = this.lambdaQuery().eq(BusinessVaccine::getOwnerId, ownerId).list();

        // 提取所有宠物ID和宠物主人ID以便后续批量查询
        Set<Integer> petIds = businessVaccines.stream()
                .map(BusinessVaccine::getPetId)
                .collect(Collectors.toSet());

        Set<Integer> ownerIds = businessVaccines.stream()
                .map(BusinessVaccine::getOwnerId)
                .collect(Collectors.toSet());

        // 批量查询宠物和宠物主人信息
        Map<Integer, BusinessPets> petsById = businessPetsService.listByIds(new ArrayList<>(petIds)).stream()
                .collect(Collectors.toMap(BusinessPets::getId, Function.identity()));

        Map<Integer, BusinessOwners> ownersById = businessOwnersService.listByIds(new ArrayList<>(ownerIds)).stream()
                .collect(Collectors.toMap(BusinessOwners::getId, Function.identity()));

        // 构建VaccineEmailNotify列表
        return businessVaccines.stream().map(businessVaccine -> {
            EmailNotify emailNotify = new EmailNotify();
            BusinessPets pet = petsById.get(businessVaccine.getPetId());
            BusinessOwners owner = ownersById.get(businessVaccine.getOwnerId());

            if (pet != null) {
                emailNotify.setPetName(pet.getName());
            }
            if (owner != null) {
                emailNotify.setPetOwnerName(owner.getName());
                emailNotify.setEmail(owner.getEmail());
            }
            emailNotify.setNextDate(businessVaccine.getNextDate());
            emailNotify.setEventType("接种疫苗");
            emailNotify.setDate(businessVaccine.getNextDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            return emailNotify;
        }).collect(Collectors.toList());
    }
}




