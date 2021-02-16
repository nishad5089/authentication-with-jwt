package com.commonauthmodule.commonauthmodule.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {
    @GetMapping("hellouser")
    public String home(){
        return "Hello User";
    }
    @GetMapping("helloadmin")
    public String index(){
        return "Hello Admin";
    }
}
