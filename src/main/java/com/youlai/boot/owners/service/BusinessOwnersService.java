package com.youlai.boot.owners.service;

import com.youlai.boot.owners.model.entity.BusinessOwners;
import com.youlai.boot.owners.model.form.BusinessOwnersForm;
import com.youlai.boot.owners.model.query.BusinessOwnersQuery;
import com.youlai.boot.owners.model.vo.BusinessOwnersVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 主人信息服务类
 *
 * @author lwj
 * @since 2025-02-19 21:28
 */
public interface BusinessOwnersService extends IService<BusinessOwners> {

    /**
     *主人信息分页列表
     *
     * @return
     */
    IPage<BusinessOwnersVO> getBusinessOwnersPage(BusinessOwnersQuery queryParams);

    /**
     * 获取主人信息表单数据
     *
     * @param id 主人信息ID
     * @return
     */
     BusinessOwnersForm getBusinessOwnersFormData(Long id);

    /**
     * 新增主人信息
     *
     * @param formData 主人信息表单对象
     * @return
     */
    boolean saveBusinessOwners(BusinessOwnersForm formData);

    /**
     * 修改主人信息
     *
     * @param id   主人信息ID
     * @param formData 主人信息表单对象
     * @return
     */
    boolean updateBusinessOwners(Long id, BusinessOwnersForm formData);

    /**
     * 删除主人信息
     *
     * @param ids 主人信息ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteBusinessOwnerss(String ids);

}
