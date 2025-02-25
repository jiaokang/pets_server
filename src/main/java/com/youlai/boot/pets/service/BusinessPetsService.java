package com.youlai.boot.pets.service;

import com.youlai.boot.pets.model.entity.BusinessPets;
import com.youlai.boot.pets.model.form.BusinessPetsForm;
import com.youlai.boot.pets.model.query.BusinessPetsQuery;
import com.youlai.boot.pets.model.vo.BusinessPetsVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 宠物信息服务类
 *
 * @author lwj
 * @since 2025-02-24 21:57
 */
public interface BusinessPetsService extends IService<BusinessPets> {

    /**
     *宠物信息分页列表
     *
     * @return
     */
    IPage<BusinessPetsVO> getBusinessPetsPage(BusinessPetsQuery queryParams);

    /**
     * 获取宠物信息表单数据
     *
     * @param id 宠物信息ID
     * @return
     */
     BusinessPetsForm getBusinessPetsFormData(Long id);

    /**
     * 新增宠物信息
     *
     * @param formData 宠物信息表单对象
     * @return
     */
    boolean saveBusinessPets(BusinessPetsForm formData);

    /**
     * 修改宠物信息
     *
     * @param id   宠物信息ID
     * @param formData 宠物信息表单对象
     * @return
     */
    boolean updateBusinessPets(Long id, BusinessPetsForm formData);

    /**
     * 删除宠物信息
     *
     * @param ids 宠物信息ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteBusinessPetss(String ids);

}
