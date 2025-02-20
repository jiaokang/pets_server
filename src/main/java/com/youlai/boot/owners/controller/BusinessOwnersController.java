package com.youlai.boot.owners.controller;

import com.youlai.boot.owners.service.BusinessOwnersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.youlai.boot.owners.model.form.BusinessOwnersForm;
import com.youlai.boot.owners.model.query.BusinessOwnersQuery;
import com.youlai.boot.owners.model.vo.BusinessOwnersVO;
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
 * 主人信息前端控制层
 *
 * @author lwj
 * @since 2025-02-19 21:28
 */
@Tag(name = "主人信息接口")
@RestController
@RequestMapping("/api/v1/businessOwnerss")
@RequiredArgsConstructor
public class BusinessOwnersController  {

    private final BusinessOwnersService businessOwnersService;

    @Operation(summary = "主人信息分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPerm('businessOwners:businessOwners:query')")
    public PageResult<BusinessOwnersVO> getBusinessOwnersPage(BusinessOwnersQuery queryParams ) {
        IPage<BusinessOwnersVO> result = businessOwnersService.getBusinessOwnersPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增主人信息")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('businessOwners:businessOwners:add')")
    public Result<Void> saveBusinessOwners(@RequestBody @Valid BusinessOwnersForm formData ) {
        boolean result = businessOwnersService.saveBusinessOwners(formData);
        return Result.judge(result);
    }

    @Operation(summary = "获取主人信息表单数据")
    @GetMapping("/{id}/form")
    @PreAuthorize("@ss.hasPerm('businessOwners:businessOwners:edit')")
    public Result<BusinessOwnersForm> getBusinessOwnersForm(
        @Parameter(description = "主人信息ID") @PathVariable Long id
    ) {
        BusinessOwnersForm formData = businessOwnersService.getBusinessOwnersFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改主人信息")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('businessOwners:businessOwners:edit')")
    public Result<Void> updateBusinessOwners(
            @Parameter(description = "主人信息ID") @PathVariable Long id,
            @RequestBody @Validated BusinessOwnersForm formData
    ) {
        boolean result = businessOwnersService.updateBusinessOwners(id, formData);
        return Result.judge(result);
    }

    @Operation(summary = "删除主人信息")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('businessOwners:businessOwners:delete')")
    public Result<Void> deleteBusinessOwnerss(
        @Parameter(description = "主人信息ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = businessOwnersService.deleteBusinessOwnerss(ids);
        return Result.judge(result);
    }
}
