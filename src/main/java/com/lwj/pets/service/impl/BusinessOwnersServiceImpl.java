package com.lwj.pets.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwj.pets.domain.BusinessOwners;
import com.lwj.pets.service.BusinessOwnersService;
import com.lwj.pets.mapper.BusinessOwnersMapper;
import org.springframework.stereotype.Service;

/**
* @author 81170
* @description 针对表【business_owners(主人信息表)】的数据库操作Service实现
* @createDate 2025-02-26 21:12:07
*/
@Service
public class BusinessOwnersServiceImpl extends ServiceImpl<BusinessOwnersMapper, BusinessOwners>
    implements BusinessOwnersService{

}




