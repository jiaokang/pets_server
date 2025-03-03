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
import com.lwj.pets.req.vaccine.EditVaccineParam;
import com.lwj.pets.res.PetResult;
import com.lwj.pets.res.vaccine.VaccineRecord;
import com.lwj.pets.service.BusinessPetsService;
import com.lwj.pets.service.BusinessVaccineService;
import com.lwj.pets.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    private int getVaccineGap(LocalDate vaccineDate, LocalDate nextDate) {
        // 确保 nextDate 晚于 vaccineDate
        if (nextDate.isBefore(vaccineDate)) {
            throw new IllegalArgumentException("nextDate must be after vaccineDate");
        }
        // 计算两个日期相差的总月数
        return (int) ChronoUnit.MONTHS.between(vaccineDate, nextDate);
    }

    public List<VaccineRecord> getVaccineRecord(Integer petId, String token) {
        Integer ownerId = TokenUtils.getOwnerId(token);
        List<BusinessVaccine> businessVaccineList;
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
                vaccineRecord.setPetId(businessVaccine.getPetId());
                vaccineRecord.setPetName(businessPetsService.getById(businessVaccine.getPetId()).getName());
                vaccineRecord.setVaccineName(businessVaccine.getVaccineName());
                vaccineRecord.setVaccineDate(businessVaccine.getVaccineDate());
                vaccineRecord.setNextDate(businessVaccine.getNextDate());
                vaccineRecord.setHospital(businessVaccine.getHospital());
                vaccineRecord.setNotes(businessVaccine.getNotes());
                vaccineRecord.setGap(getVaccineGap(businessVaccine.getVaccineDate(), businessVaccine.getNextDate()));
                return vaccineRecord;
            }).collect(Collectors.toList());
        }
    }

    /**
     * 删除接种记录
     */
    public void deleteVaccineRecord(Integer id, String token) {
        Integer ownerId = TokenUtils.getOwnerId(token);
        businessVaccineService.lambdaUpdate().eq(BusinessVaccine::getId, id).eq(BusinessVaccine::getOwnerId, ownerId).remove();
    }

    /**
     * 修改接种记录
     */
    public void updateVaccineRecord(EditVaccineParam editVaccineParam, String token) {
        Integer ownerId = TokenUtils.getOwnerId(token);
        BusinessVaccine businessVaccine = businessVaccineService.getById(editVaccineParam.getId());
        if (businessVaccine == null) {
            log.error("接种记录不存在");
            throw new BusinessException(ResultCode.VACCINE_NOT_FOUND);
        }
        businessVaccine.setVaccineName(editVaccineParam.getVaccineName());
        businessVaccine.setVaccineDate(editVaccineParam.getVaccineDate());
        businessVaccine.setHospital(editVaccineParam.getHospital());
        businessVaccine.setNextDate(nextVaccineDate(editVaccineParam.getVaccineDate(), editVaccineParam.getGap()));
        businessVaccine.setNotes(editVaccineParam.getNotes());
        businessVaccine.setPetId(editVaccineParam.getPetId());
        businessVaccineService.updateById(businessVaccine);

    }
}
