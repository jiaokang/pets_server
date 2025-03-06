package com.lwj.pets.logic;

import com.lwj.pets.common.ResultCode;
import com.lwj.pets.domain.BusinessDeworming;
import com.lwj.pets.domain.BusinessPets;
import com.lwj.pets.domain.BusinessVaccine;
import com.lwj.pets.exception.BusinessException;
import com.lwj.pets.req.deworm.AddDewormParam;
import com.lwj.pets.req.deworm.EditDewormParam;
import com.lwj.pets.req.vaccine.AddVaccineParam;
import com.lwj.pets.req.vaccine.EditVaccineParam;
import com.lwj.pets.res.deworm.DewormRecord;
import com.lwj.pets.res.vaccine.VaccineRecord;
import com.lwj.pets.service.BusinessDewormingService;
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

import static com.lwj.pets.utils.DateUtils.getGap;
import static com.lwj.pets.utils.DateUtils.nextDate;

@Slf4j
@Component
public class DewormLogic {

    @Autowired
    private BusinessPetsService businessPetsService;
    @Autowired
    private BusinessDewormingService businessDewormingService;

    /*
     * 添加驱虫记录
     */
    public void addDewormRecord(AddDewormParam addDewormParam, String token) {
        Integer ownerId = TokenUtils.getOwnerId(token);
        BusinessPets businessPets = businessPetsService.getById(addDewormParam.getPetId());
        if (businessPets == null) {
            log.error("宠物不存在");
            throw new BusinessException(ResultCode.PET_NOT_FOUND);
        }
        BusinessDeworming businessDeworming = new BusinessDeworming();
        businessDeworming.setOwnerId(ownerId);
        businessDeworming.setPetId(addDewormParam.getPetId());
        businessDeworming.setDewormType(addDewormParam.getDewormType());
        businessDeworming.setProductName(addDewormParam.getProductName());
        businessDeworming.setMetering(addDewormParam.getMetering());
        businessDeworming.setWeight(addDewormParam.getWeight());
        businessDeworming.setDewormDate(addDewormParam.getDewormDate());
        businessDeworming.setAddress(addDewormParam.getAddress());
        businessDeworming.setNotes(addDewormParam.getNotes());
        businessDeworming.setNextDate(nextDate(addDewormParam.getDewormDate(), addDewormParam.getGap()));
        businessDewormingService.save(businessDeworming);
    }

    /*
     * 获取驱虫记录
     */
    public List<DewormRecord> getDewormRecord(Integer petId, String token) {
        Integer ownerId = TokenUtils.getOwnerId(token);
        List<BusinessDeworming> businessDewormingList;
        if (petId == null) {
            businessDewormingList = businessDewormingService.lambdaQuery().eq(BusinessDeworming::getOwnerId, ownerId).list();
        } else {
            businessDewormingList = businessDewormingService.lambdaQuery().eq(BusinessDeworming::getOwnerId, ownerId).eq(BusinessDeworming::getPetId, petId).list();
        }
        if (businessDewormingList.isEmpty()) {
            log.error("没有驱虫记录");
            return new ArrayList<>();
        } else {
            return businessDewormingList.stream().map(businessDeworming -> {
                DewormRecord dewormRecord = new DewormRecord();
                dewormRecord.setId(businessDeworming.getId());
                dewormRecord.setPetId(businessDeworming.getPetId());
                dewormRecord.setPetName(businessPetsService.getById(businessDeworming.getPetId()).getName());
                dewormRecord.setDewormType(businessDeworming.getDewormType());
                dewormRecord.setProductName(businessDeworming.getProductName());
                dewormRecord.setMetering(businessDeworming.getMetering());
                dewormRecord.setWeight(businessDeworming.getWeight());
                dewormRecord.setDewormDate(businessDeworming.getDewormDate());
                dewormRecord.setNextDate(businessDeworming.getNextDate());
                dewormRecord.setAddress(businessDeworming.getAddress());
                dewormRecord.setNotes(businessDeworming.getNotes());
                dewormRecord.setGap(getGap(businessDeworming.getDewormDate(), businessDeworming.getNextDate()));
                return dewormRecord;
            }).collect(Collectors.toList());
        }
    }

    public void deleteDewormRecord(Integer id, String token) {
        Integer ownerId = TokenUtils.getOwnerId(token);
        businessDewormingService.lambdaUpdate().eq(BusinessDeworming::getId, id).eq(BusinessDeworming::getOwnerId, ownerId).remove();
    }

    public void updateDewormRecord(EditDewormParam editDewormParam, String token) {
        Integer ownerId = TokenUtils.getOwnerId(token);
        businessDewormingService.lambdaQuery().eq(BusinessDeworming::getId, editDewormParam.getId()).eq(BusinessDeworming::getOwnerId, ownerId)
                .oneOpt().ifPresent(businessDeworming -> {
                    businessDeworming.setPetId(editDewormParam.getPetId());
                    businessDeworming.setDewormType(editDewormParam.getDewormType());
                    businessDeworming.setProductName(editDewormParam.getProductName());
                    businessDeworming.setMetering(editDewormParam.getMetering());
                    businessDeworming.setWeight(editDewormParam.getWeight());
                    businessDeworming.setDewormDate(editDewormParam.getDewormDate());
                    businessDeworming.setAddress(editDewormParam.getAddress());
                    businessDeworming.setNotes(editDewormParam.getNotes());
                    businessDeworming.setNextDate(nextDate(editDewormParam.getDewormDate(), editDewormParam.getGap()));
                    businessDewormingService.updateById(businessDeworming);
                });
    }
}
