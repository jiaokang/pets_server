package com.youlai.boot.pets.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youlai.boot.pets.model.entity.BusinessPets;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.pets.model.query.BusinessPetsQuery;
import com.youlai.boot.pets.model.vo.BusinessPetsVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 宠物信息Mapper接口
 *
 * @author lwj
 * @since 2025-02-24 21:57
 */
@Mapper
public interface BusinessPetsMapper extends BaseMapper<BusinessPets> {

    /**
     * 获取宠物信息分页数据
     *
     * @param page 分页对象
     * @param queryParams 查询参数
     * @return
     */
    Page<BusinessPetsVO> getBusinessPetsPage(Page<BusinessPetsVO> page, BusinessPetsQuery queryParams);

}
