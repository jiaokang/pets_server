package com.youlai.boot.owners.model.form;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;

/**
 * 主人信息表单对象
 *
 * @author lwj
 * @since 2025-02-19 21:28
 */
@Getter
@Setter
@Schema(description = "主人信息表单对象")
public class BusinessOwnersForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主人唯一标识")
    private Integer id;

    @Schema(description = "主人姓名")
    @NotBlank(message = "主人姓名不能为空")
    @Size(max=50, message="主人姓名长度不能超过50个字符")
    private String name;

    @Schema(description = "主人手机号")
    @NotBlank(message = "主人手机号不能为空")
    @Size(max=20, message="主人手机号长度不能超过20个字符")
    private String phone;

    @Schema(description = "主人邮箱")
    @Size(max=100, message="主人邮箱长度不能超过100个字符")
    private String email;

    @Schema(description = "主人地址")
    @Size(max=65535, message="主人地址长度不能超过65535个字符")
    private String address;




}
