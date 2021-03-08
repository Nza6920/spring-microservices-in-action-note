package com.niu.spring.outside.controller;

import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 主页
 *
 * @author [nza]
 * @version 1.0 [2021/03/08 16:52]
 * @createTime [2021/03/08 16:52]
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping()
    public Map<String, Object> home() {
        HashMap<String, Object> res = Maps.newHashMap();
        res.put("msg", "success");
        res.put("status", 1);
        return res;
    }
}
