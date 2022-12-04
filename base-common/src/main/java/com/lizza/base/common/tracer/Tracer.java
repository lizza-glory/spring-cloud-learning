package com.lizza.base.common.tracer;

import java.util.HashMap;
import java.util.Map;

import static com.lizza.base.common.util.Constants.SERVER_TYPE;

public class Tracer {

    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new ThreadLocal<>();

    public static void init() {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map != null) {
            return;
        }
        THREAD_LOCAL.set(new HashMap<>());
    }

    public static void setServerType(String type) {
        init();
        Map<String, Object> map = THREAD_LOCAL.get();
        map.put(SERVER_TYPE, type);
    }

    public static String getServerType() {
        return THREAD_LOCAL.get().get(SERVER_TYPE).toString();
    }
}
