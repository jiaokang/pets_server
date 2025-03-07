package com.lwj.pets.service;

import com.lwj.pets.domain.BusinessVaccine;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwj.pets.tasks.EmailNotify;

import java.util.List;

/**
* @author 81170
* @description 针对表【business_vaccine(宠物疫苗记录表)】的数据库操作Service
* @createDate 2025-03-02 21:21:55
*/
public interface BusinessVaccineService extends IService<BusinessVaccine> {

    List<BusinessVaccine> getVaccineRecordByOwnerId(Integer ownerId);


    List<EmailNotify> getVaccineNotifyByOwnerId(Integer ownerId);
}
