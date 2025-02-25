package com.youlai.boot.pets.model.query;

import com.youlai.boot.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 宠物信息分页查询对象
 *
 * @author lwj
 * @since 2025-02-24 21:57
 */
@Schema(description ="宠物信息查询对象")
@Getter
@Setter
public class BusinessPetsQuery extends BasePageQuery {

}
