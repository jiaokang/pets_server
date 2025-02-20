package com.youlai.boot.owners.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youlai.boot.owners.model.entity.BusinessOwners;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.owners.model.query.BusinessOwnersQuery;
import com.youlai.boot.owners.model.vo.BusinessOwnersVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 主人信息Mapper接口
 *
 * @author lwj
 * @since 2025-02-19 21:28
 */
@Mapper
public interface BusinessOwnersMapper extends BaseMapper<BusinessOwners> {

    /**
     * 获取主人信息分页数据
     *
     * @param page 分页对象
     * @param queryParams 查询参数
     * @return
     */
    Page<BusinessOwnersVO> getBusinessOwnersPage(Page<BusinessOwnersVO> page, BusinessOwnersQuery queryParams);

}
