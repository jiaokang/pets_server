package com.youlai.boot.pets.controller;

import com.youlai.boot.pets.service.BusinessPetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.youlai.boot.pets.model.form.BusinessPetsForm;
import com.youlai.boot.pets.model.query.BusinessPetsQuery;
import com.youlai.boot.pets.model.vo.BusinessPetsVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.boot.common.result.PageResult;
import com.youlai.boot.common.result.Result;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

/**
 * 宠物信息前端控制层
 *
 * @author lwj
 * @since 2025-02-24 21:57
 */
@Tag(name = "宠物信息接口")
@RestController
@RequestMapping("/api/v1/businessPetss")
@RequiredArgsConstructor
public class BusinessPetsController  {

    private final BusinessPetsService businessPetsService;

    @Operation(summary = "宠物信息分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPerm('pets:businessPets:query')")
    public PageResult<BusinessPetsVO> getBusinessPetsPage(BusinessPetsQuery queryParams ) {
        IPage<BusinessPetsVO> result = businessPetsService.getBusinessPetsPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增宠物信息")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('pets:businessPets:add')")
    public Result<Void> saveBusinessPets(@RequestBody @Valid BusinessPetsForm formData ) {
        boolean result = businessPetsService.saveBusinessPets(formData);
        return Result.judge(result);
    }

    @Operation(summary = "获取宠物信息表单数据")
    @GetMapping("/{id}/form")
    @PreAuthorize("@ss.hasPerm('pets:businessPets:edit')")
    public Result<BusinessPetsForm> getBusinessPetsForm(
        @Parameter(description = "宠物信息ID") @PathVariable Long id
    ) {
        BusinessPetsForm formData = businessPetsService.getBusinessPetsFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改宠物信息")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('pets:businessPets:edit')")
    public Result<Void> updateBusinessPets(
            @Parameter(description = "宠物信息ID") @PathVariable Long id,
            @RequestBody @Validated BusinessPetsForm formData
    ) {
        boolean result = businessPetsService.updateBusinessPets(id, formData);
        return Result.judge(result);
    }

    @Operation(summary = "删除宠物信息")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('pets:businessPets:delete')")
    public Result<Void> deleteBusinessPetss(
        @Parameter(description = "宠物信息ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = businessPetsService.deleteBusinessPetss(ids);
        return Result.judge(result);
    }
}
