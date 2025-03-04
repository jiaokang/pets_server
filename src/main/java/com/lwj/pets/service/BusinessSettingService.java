package com.lwj.pets.service;

import com.lwj.pets.domain.BusinessSetting;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 81170
* @description 针对表【business_setting(设置项目)】的数据库操作Service
* @createDate 2025-03-03 20:49:58
*/
public interface BusinessSettingService extends IService<BusinessSetting> {

    /**
     * 获取指定值的设置项目
     */
    List<BusinessSetting> getSpecialSetting(String settingCode,String settingValue);

}
