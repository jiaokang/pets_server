package com.lwj.pets.service;

import com.lwj.pets.domain.BusinessDeworming;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwj.pets.tasks.EmailNotify;

import java.util.List;

/**
* @author 81170
* @description 针对表【business_deworming(宠物驱虫记录表)】的数据库操作Service
* @createDate 2025-03-04 22:16:10
*/
public interface BusinessDewormingService extends IService<BusinessDeworming> {

    List<EmailNotify> getDewormingNotifyByOwnerId(Integer ownerId);
}
