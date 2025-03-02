package com.lwj.pets.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwj.pets.domain.BusinessPets;
import com.lwj.pets.service.BusinessPetsService;
import com.lwj.pets.mapper.BusinessPetsMapper;
import org.springframework.stereotype.Service;

/**
* @author 81170
* @description 针对表【business_pets(宠物信息表)】的数据库操作Service实现
* @createDate 2025-03-02 11:50:01
*/
@Service
public class BusinessPetsServiceImpl extends ServiceImpl<BusinessPetsMapper, BusinessPets>
    implements BusinessPetsService{

}




