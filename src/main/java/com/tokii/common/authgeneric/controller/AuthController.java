package com.tokii.common.authgeneric.controller;

import com.tokii.common.authgeneric.param.User;
import com.tokii.common.authgeneric.response.PlainResponse;
import com.tokii.common.authgeneric.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
public class AuthController {

    @PostMapping("/api/user")
    public PlainResponse testUser(@RequestBody String  user) {
        return ResponseUtils.buildOkWithData(UUID.randomUUID().toString(), user);
    }

    @PostMapping("/api/admin")
    public PlainResponse testAdmin(@RequestParam User user) {
        return ResponseUtils.buildOkWithData(UUID.randomUUID().toString(), user);
    }

}
