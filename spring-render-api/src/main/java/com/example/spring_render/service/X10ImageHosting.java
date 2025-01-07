package com.example.spring_render.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * Created by yana.zhmak on 20.12.2024.
 */
@Component
//@Profile("render")
public class X10ImageHosting implements ImageHosting {

    @Override
    public String save(InputStream is) throws Exception {
        return "url";
    }

}
