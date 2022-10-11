package com.example.demo.base.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ControllerConfig {
    public static final Map<String, String> map = new ConcurrentHashMap();

    public ControllerConfig() {
    }

    public ControllerConfig setConfig(String key, String value) {
        map.put(key, value);
        return this;
    }

    public Map<String, String> getConfig() {
        return map;
    }
}
