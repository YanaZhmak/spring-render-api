package com.example.spring_render.service;

import java.io.InputStream;

/**
 * Created by yana.zhmak on 20.12.2024.
 */
public interface ImageHosting {
    String save(InputStream is) throws Exception;
}
