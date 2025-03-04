package com.lwj.pets.api;


import com.lwj.pets.common.Result;
import com.lwj.pets.logic.DewormLogic;
import com.lwj.pets.logic.VaccineLogic;
import com.lwj.pets.req.deworm.AddDewormParam;
import com.lwj.pets.req.vaccine.AddVaccineParam;
import com.lwj.pets.req.vaccine.EditVaccineParam;
import com.lwj.pets.res.deworm.DewormRecord;
import com.lwj.pets.res.vaccine.VaccineRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/deworm")
public class DewormApiController {

    @Autowired
    DewormLogic dewormLogic;


    /**
     * 添加驱虫信息
     */
    @PostMapping("/add")
    public Result<Void> login(@RequestBody AddDewormParam addDewormParam, @RequestHeader("Authorization") String token) {
        dewormLogic.addDewormRecord(addDewormParam,token);
        return Result.success();
    }

    /**
     * 获取接种信息
     */
    @GetMapping("/get")
    public Result<List<DewormRecord>> getPets(@RequestParam(value ="petId",required = false)Integer petId, @RequestHeader("Authorization") String token) {
        List<DewormRecord> result = dewormLogic.getDewormRecord(petId,token);
        return Result.success(result);
    }



//    /**
//     * 删除接种信息
//     */
//    @PostMapping("/delete")
//    public Result<Void> deletePet(@RequestBody EditVaccineParam deleteVaccineParam, @RequestHeader("Authorization") String token) {
//        vaccineLogic.deleteVaccineRecord(deleteVaccineParam.getId(),token);
//        return Result.success();
//    }
//    /**
//     * 修改接种信息
//     */
//    @PostMapping("/update")
//    public Result<Void> updatePet(@RequestBody EditVaccineParam editVaccineParam, @RequestHeader("Authorization") String token) {
//        vaccineLogic.updateVaccineRecord(editVaccineParam,token);
//        return Result.success();
//    }

}
