package com.example.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by qidd on 2018-3-17
 */
public class JsonUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        return gson.toJson(object);


    }
}
