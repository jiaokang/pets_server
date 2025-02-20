package com.youlai.boot.owners.model.query;

import com.youlai.boot.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 主人信息分页查询对象
 *
 * @author lwj
 * @since 2025-02-19 21:28
 */
@Schema(description ="主人信息查询对象")
@Getter
@Setter
public class BusinessOwnersQuery extends BasePageQuery {

}
