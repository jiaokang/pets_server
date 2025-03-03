package com.lwj.pets.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwj.pets.domain.BusinessVaccine;
import com.lwj.pets.service.BusinessVaccineService;
import com.lwj.pets.mapper.BusinessVaccineMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 81170
* @description 针对表【business_vaccine(宠物疫苗记录表)】的数据库操作Service实现
* @createDate 2025-03-02 21:21:55
*/
@Service
public class BusinessVaccineServiceImpl extends ServiceImpl<BusinessVaccineMapper, BusinessVaccine>
    implements BusinessVaccineService{

    @Override
    public List<BusinessVaccine> getVaccineRecordByOwnerId(Integer ownerId) {
        return this.lambdaQuery().eq(BusinessVaccine::getOwnerId,ownerId).list();
    }
}




