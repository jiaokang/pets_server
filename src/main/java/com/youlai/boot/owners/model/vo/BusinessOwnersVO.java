package com.youlai.boot.owners.model.vo;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * 主人信息视图对象
 *
 * @author lwj
 * @since 2025-02-19 21:28
 */
@Getter
@Setter
@Schema( description = "主人信息视图对象")
public class BusinessOwnersVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主人唯一标识")
    private Integer id;
    @Schema(description = "主人姓名")
    private String name;
    @Schema(description = "主人手机号")
    private String phone;
    @Schema(description = "主人邮箱")
    private String email;
    @Schema(description = "主人地址")
    private String address;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @Schema(description = "创建时间")
    private LocalDateTime updateTime;
}
