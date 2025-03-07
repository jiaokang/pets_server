package com.lwj.pets.api;


import com.lwj.pets.common.Result;
import com.lwj.pets.logic.AuthLogic;
import com.lwj.pets.logic.PetsLogic;
import com.lwj.pets.req.AddPetParam;
import com.lwj.pets.req.LoginParam;
import com.lwj.pets.req.UpdatePetParam;
import com.lwj.pets.res.PetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.DocFlavor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pets")
public class PetsApiController {

    @Autowired
    PetsLogic petsLogic;

    /**
     * 添加宠物
     */
    @PostMapping("/add")
    public Result<Map<String, Object>> login(@RequestBody AddPetParam addPetParam, @RequestHeader("Authorization") String token) {
        petsLogic.addPet(addPetParam, token);
        return Result.success();
    }

    /**
     * 获取宠物列表
     */
    @GetMapping("/get")
    public Result<List<PetResult>> getPets(@RequestHeader("Authorization") String token) {
        List<PetResult> result = petsLogic.petList(token);
        return Result.success(result);
    }

    /**
     * 删除宠物
     */
    @PostMapping("delete")
    public Result<Void> deletePet(@RequestBody Map<String, Integer> map, @RequestHeader("Authorization") String token) {
        Integer petId = map.get("id");
        petsLogic.deletePet(petId, token);
        return Result.success();
    }

    /**
     * 更新宠物
     */
    @PostMapping("/update")
    public Result<Void> updatePet(@RequestBody UpdatePetParam updatePetParam, @RequestHeader("Authorization") String token) {
        petsLogic.updatePet(updatePetParam, token);
        return Result.success();
    }
}
