package com.lwj.pets.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwj.pets.common.SettingItemEnum;
import com.lwj.pets.domain.BusinessSetting;
import com.lwj.pets.service.BusinessSettingService;
import com.lwj.pets.mapper.BusinessSettingMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 81170
 * @description 针对表【business_setting(设置项目)】的数据库操作Service实现
 * @createDate 2025-03-03 20:49:58
 */
@Service
public class BusinessSettingServiceImpl extends ServiceImpl<BusinessSettingMapper, BusinessSetting>
        implements BusinessSettingService {

    @Override
    public List<BusinessSetting> getSpecialSetting(String settingCode, String settingValue) {
        return this.lambdaQuery().eq(BusinessSetting::getSettingCode, settingCode).eq(BusinessSetting::getSettingValue, settingValue).list();
    }

    @Override
    public void initSettingItem(Integer ownerId) {
        List<BusinessSetting> businessSettings = new ArrayList<>();
        for (SettingItemEnum item : SettingItemEnum.values()) {
            BusinessSetting businessSetting = new BusinessSetting();
            businessSetting.setOwnerId(ownerId);
            businessSetting.setSettingName(item.getName());
            businessSetting.setSettingCode(item.getCode());
            businessSetting.setSettingValue(item.getValue());
            businessSettings.add(businessSetting);
        }
        this.saveBatch(businessSettings);
    }
}




