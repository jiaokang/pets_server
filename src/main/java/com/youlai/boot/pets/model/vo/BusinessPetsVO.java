package com.youlai.boot.pets.model.vo;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


/**
 * 宠物信息视图对象
 *
 * @author lwj
 * @since 2025-02-24 21:57
 */
@Getter
@Setter
@Schema( description = "宠物信息视图对象")
public class BusinessPetsVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "宠物唯一标识")
    private Integer id;
    @Schema(description = "宠物名称")
    private String name;
    @Schema(description = "宠物种类（猫/狗/其他）")
    private String species;
    @Schema(description = "宠物品种")
    private String breed;
    @Schema(description = "宠物出生日期")
    private LocalDate birthDate;
    @Schema(description = "关联主人ID")
    private Integer ownerId;
    @Schema(description = "宠物照片路径")
    private String profileImagePath;
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
