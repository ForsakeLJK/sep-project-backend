package com.sep.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sep")
public class HomeController {

    @GetMapping("/home")
    public JSONObject homePage() {
        JSONObject result = new JSONObject();
        result.put("result", "success");
        return result;
    }
}
