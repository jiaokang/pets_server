package com.youlai.boot.owners.converter;

import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.owners.model.entity.BusinessOwners;
import com.youlai.boot.owners.model.form.BusinessOwnersForm;

/**
 * 主人信息对象转换器
 *
 * @author lwj
 * @since 2025-02-19 21:28
 */
@Mapper(componentModel = "spring")
public interface BusinessOwnersConverter{

    BusinessOwnersForm toForm(BusinessOwners entity);

    BusinessOwners toEntity(BusinessOwnersForm formData);
}