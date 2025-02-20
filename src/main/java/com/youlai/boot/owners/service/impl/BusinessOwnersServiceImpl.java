package com.youlai.boot.owners.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.owners.mapper.BusinessOwnersMapper;
import com.youlai.boot.owners.service.BusinessOwnersService;
import com.youlai.boot.owners.model.entity.BusinessOwners;
import com.youlai.boot.owners.model.form.BusinessOwnersForm;
import com.youlai.boot.owners.model.query.BusinessOwnersQuery;
import com.youlai.boot.owners.model.vo.BusinessOwnersVO;
import com.youlai.boot.owners.converter.BusinessOwnersConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

/**
 * 主人信息服务实现类
 *
 * @author lwj
 * @since 2025-02-19 21:28
 */
@Service
@RequiredArgsConstructor
public class BusinessOwnersServiceImpl extends ServiceImpl<BusinessOwnersMapper, BusinessOwners> implements BusinessOwnersService {

    private final BusinessOwnersConverter businessOwnersConverter;

    /**
    * 获取主人信息分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<BusinessOwnersVO>} 主人信息分页列表
    */
    @Override
    public IPage<BusinessOwnersVO> getBusinessOwnersPage(BusinessOwnersQuery queryParams) {
        Page<BusinessOwnersVO> pageVO = this.baseMapper.getBusinessOwnersPage(
                new Page<>(queryParams.getPageNum(), queryParams.getPageSize()),
                queryParams
        );
        return pageVO;
    }
    
    /**
     * 获取主人信息表单数据
     *
     * @param id 主人信息ID
     * @return
     */
    @Override
    public BusinessOwnersForm getBusinessOwnersFormData(Long id) {
        BusinessOwners entity = this.getById(id);
        return businessOwnersConverter.toForm(entity);
    }
    
    /**
     * 新增主人信息
     *
     * @param formData 主人信息表单对象
     * @return
     */
    @Override
    public boolean saveBusinessOwners(BusinessOwnersForm formData) {
        BusinessOwners entity = businessOwnersConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新主人信息
     *
     * @param id   主人信息ID
     * @param formData 主人信息表单对象
     * @return
     */
    @Override
    public boolean updateBusinessOwners(Long id,BusinessOwnersForm formData) {
        BusinessOwners entity = businessOwnersConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除主人信息
     *
     * @param ids 主人信息ID，多个以英文逗号(,)分割
     * @return
     */
    @Override
    public boolean deleteBusinessOwnerss(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的主人信息数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

}
