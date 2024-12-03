package com.example.spring_render_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yana.zhmak on 03.12.2024.
 */

@RestController
public class DemoController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Yana!";
    }

}
