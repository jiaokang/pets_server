package com.youlai.boot.pets.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.pets.mapper.BusinessPetsMapper;
import com.youlai.boot.pets.service.BusinessPetsService;
import com.youlai.boot.pets.model.entity.BusinessPets;
import com.youlai.boot.pets.model.form.BusinessPetsForm;
import com.youlai.boot.pets.model.query.BusinessPetsQuery;
import com.youlai.boot.pets.model.vo.BusinessPetsVO;
import com.youlai.boot.pets.converter.BusinessPetsConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

/**
 * 宠物信息服务实现类
 *
 * @author lwj
 * @since 2025-02-24 21:57
 */
@Service
@RequiredArgsConstructor
public class BusinessPetsServiceImpl extends ServiceImpl<BusinessPetsMapper, BusinessPets> implements BusinessPetsService {

    private final BusinessPetsConverter businessPetsConverter;

    /**
    * 获取宠物信息分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<BusinessPetsVO>} 宠物信息分页列表
    */
    @Override
    public IPage<BusinessPetsVO> getBusinessPetsPage(BusinessPetsQuery queryParams) {
        Page<BusinessPetsVO> pageVO = this.baseMapper.getBusinessPetsPage(
                new Page<>(queryParams.getPageNum(), queryParams.getPageSize()),
                queryParams
        );
        return pageVO;
    }
    
    /**
     * 获取宠物信息表单数据
     *
     * @param id 宠物信息ID
     * @return
     */
    @Override
    public BusinessPetsForm getBusinessPetsFormData(Long id) {
        BusinessPets entity = this.getById(id);
        return businessPetsConverter.toForm(entity);
    }
    
    /**
     * 新增宠物信息
     *
     * @param formData 宠物信息表单对象
     * @return
     */
    @Override
    public boolean saveBusinessPets(BusinessPetsForm formData) {
        BusinessPets entity = businessPetsConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新宠物信息
     *
     * @param id   宠物信息ID
     * @param formData 宠物信息表单对象
     * @return
     */
    @Override
    public boolean updateBusinessPets(Long id,BusinessPetsForm formData) {
        BusinessPets entity = businessPetsConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除宠物信息
     *
     * @param ids 宠物信息ID，多个以英文逗号(,)分割
     * @return
     */
    @Override
    public boolean deleteBusinessPetss(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的宠物信息数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

}
