package com.lwj.pets.api;


import com.lwj.pets.common.Result;
import com.lwj.pets.logic.PetsLogic;
import com.lwj.pets.req.AddPetParam;
import com.lwj.pets.req.UpdatePetParam;
import com.lwj.pets.res.PetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/avatar")
public class AvatarApiController {

    @Autowired
    PetsLogic petsLogic;


    /**
     * 上传头像
     */
    @ResponseBody
    @PostMapping("/uploadAvatar")
    public Result<String> uploadAvatar(@RequestParam("file") MultipartFile file, @RequestHeader("Authorization") String token) {
        String avatarCode = petsLogic.uploadAvatar(file, token);
        return Result.success(avatarCode);
    }
    /**
     * 获取头像
     */
    @GetMapping("/avatars/{avatarCode}")
    public ResponseEntity<Resource> getAvatar(@PathVariable String avatarCode) {
        return petsLogic.getAvatar(avatarCode);
    }

}
