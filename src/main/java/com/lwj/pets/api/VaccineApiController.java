package com.lwj.pets.api;


import com.lwj.pets.common.Result;
import com.lwj.pets.logic.PetsLogic;
import com.lwj.pets.logic.VaccineLogic;
import com.lwj.pets.req.AddPetParam;
import com.lwj.pets.req.UpdatePetParam;
import com.lwj.pets.req.vaccine.AddVaccineParam;
import com.lwj.pets.req.vaccine.EditVaccineParam;
import com.lwj.pets.res.PetResult;
import com.lwj.pets.res.vaccine.VaccineRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/vaccine")
public class VaccineApiController {

    @Autowired
    VaccineLogic vaccineLogic;


    /**
     * 添加接种信息
     */
    @PostMapping("/add")
    public Result<Void> login(@RequestBody AddVaccineParam addVaccineParam, @RequestHeader("Authorization") String token) {
        vaccineLogic.addPet(addVaccineParam,token);
        return Result.success();
    }

    /**
     * 获取接种信息
     */
    @GetMapping("/get")
    public Result<List<VaccineRecord>> getPets(@RequestParam(value ="petId",required = false)Integer petId,@RequestHeader("Authorization") String token) {
        List<VaccineRecord> result = vaccineLogic.getVaccineRecord(petId,token);
        return Result.success(result);
    }
    /**
     * 删除接种信息
     */
    @PostMapping("/delete")
    public Result<Void> deletePet(@RequestBody EditVaccineParam deleteVaccineParam, @RequestHeader("Authorization") String token) {
        vaccineLogic.deleteVaccineRecord(deleteVaccineParam.getId(),token);
        return Result.success();
    }
    /**
     * 修改接种信息
     */
    @PostMapping("/update")
    public Result<Void> updatePet(@RequestBody EditVaccineParam editVaccineParam, @RequestHeader("Authorization") String token) {
        vaccineLogic.updateVaccineRecord(editVaccineParam,token);
        return Result.success();
    }

}
