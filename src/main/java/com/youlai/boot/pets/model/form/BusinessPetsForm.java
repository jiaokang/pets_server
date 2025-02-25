package com.youlai.boot.pets.model.form;

import java.io.Serial;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;

/**
 * 宠物信息表单对象
 *
 * @author lwj
 * @since 2025-02-24 21:57
 */
@Getter
@Setter
@Schema(description = "宠物信息表单对象")
public class BusinessPetsForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "宠物唯一标识")
    @NotNull(message = "宠物唯一标识不能为空")
    private Integer id;

    @Schema(description = "宠物名称")
    @NotBlank(message = "宠物名称不能为空")
    @Size(max=50, message="宠物名称长度不能超过50个字符")
    private String name;

    @Schema(description = "宠物种类（猫/狗/其他）")
    @NotBlank(message = "宠物种类（猫/狗/其他）不能为空")
    @Size(max=20, message="宠物种类（猫/狗/其他）长度不能超过20个字符")
    private String species;

    @Schema(description = "宠物品种")
    @Size(max=50, message="宠物品种长度不能超过50个字符")
    private String breed;

    @Schema(description = "宠物出生日期")
    private LocalDate birthDate;

    @Schema(description = "关联主人ID")
    @NotNull(message = "关联主人ID不能为空")
    private Integer ownerId;

    @Schema(description = "宠物照片路径")
    @Size(max=65535, message="宠物照片路径长度不能超过65535个字符")
    private String profileImagePath;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;


}
