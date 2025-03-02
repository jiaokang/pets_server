package com.lwj.pets.logic;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.lwj.pets.common.ResultCode;
import com.lwj.pets.domain.BusinessPets;
import com.lwj.pets.domain.BusinessVaccine;
import com.lwj.pets.exception.BusinessException;
import com.lwj.pets.req.AddPetParam;
import com.lwj.pets.req.UpdatePetParam;
import com.lwj.pets.req.vaccine.AddVaccineParam;
import com.lwj.pets.res.PetResult;
import com.lwj.pets.res.vaccine.VaccineRecord;
import com.lwj.pets.service.BusinessPetsService;
import com.lwj.pets.service.BusinessVaccineService;
import com.lwj.pets.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class VaccineLogic {

    @Autowired
    private BusinessPetsService businessPetsService;
    @Autowired
    private BusinessVaccineService businessVaccineService;


    /**
     * 添加接种信息
     */
    public void addPet(AddVaccineParam addVaccineParam, String token) {
        Integer ownerId = TokenUtils.getOwnerId(token);
        BusinessPets businessPets = businessPetsService.getById(addVaccineParam.getPetId());
        if (businessPets == null) {
            log.error("宠物不存在");
            throw new BusinessException(ResultCode.PET_NOT_FOUND);
        }
        BusinessVaccine businessVaccine = new BusinessVaccine();
        businessVaccine.setOwnerId(ownerId);
        businessVaccine.setPetId(addVaccineParam.getPetId());
        businessVaccine.setVaccineName(addVaccineParam.getVaccineName());
        businessVaccine.setVaccineDate(addVaccineParam.getVaccineDate());
        businessVaccine.setHospital(addVaccineParam.getHospital());
        businessVaccine.setNextDate(nextVaccineDate(addVaccineParam.getVaccineDate(), addVaccineParam.getGap()));
        businessVaccine.setNotes(addVaccineParam.getNotes());
        businessVaccineService.save(businessVaccine);
    }


    /**
     * 根据间隔月数计算下次接种时间
     */
    private LocalDate nextVaccineDate(LocalDate vaccineDate, Integer gap) {
        return vaccineDate.plusMonths(gap);
    }

    public List<VaccineRecord> getVaccineRecord(Integer petId, String token) {
        Integer ownerId = TokenUtils.getOwnerId(token);
        List<BusinessVaccine> businessVaccineList = new ArrayList<>();
        if (petId == null) {
            businessVaccineList = businessVaccineService.lambdaQuery().eq(BusinessVaccine::getOwnerId, ownerId).list();
        } else {
            businessVaccineList = businessVaccineService.lambdaQuery().eq(BusinessVaccine::getOwnerId, ownerId).eq(BusinessVaccine::getPetId, petId).list();
        }
        if (businessVaccineList.isEmpty()) {
            log.error("没有接种记录");
            return new ArrayList<>();
        } else {
            return businessVaccineList.stream().map(businessVaccine -> {
                VaccineRecord vaccineRecord = new VaccineRecord();
                vaccineRecord.setId(businessVaccine.getId());
                vaccineRecord.setPetName(businessPetsService.getById(businessVaccine.getPetId()).getName());
                vaccineRecord.setVaccineName(businessVaccine.getVaccineName());
                vaccineRecord.setVaccineDate(businessVaccine.getVaccineDate());
                vaccineRecord.setNextDate(businessVaccine.getNextDate());
                vaccineRecord.setHospital(businessVaccine.getHospital());
                vaccineRecord.setNotes(businessVaccine.getNotes());
                return vaccineRecord;
            }).collect(Collectors.toList());
        }
    }
}
