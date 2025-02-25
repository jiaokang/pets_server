package com.youlai.boot.pets.converter;

import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.pets.model.entity.BusinessPets;
import com.youlai.boot.pets.model.form.BusinessPetsForm;

/**
 * 宠物信息对象转换器
 *
 * @author lwj
 * @since 2025-02-24 21:57
 */
@Mapper(componentModel = "spring")
public interface BusinessPetsConverter{

    BusinessPetsForm toForm(BusinessPets entity);

    BusinessPets toEntity(BusinessPetsForm formData);
}