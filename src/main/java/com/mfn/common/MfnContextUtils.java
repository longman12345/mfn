package com.mfn.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by LPF on 2017/5/13.
 */

public class MfnContextUtils {
    private static final Logger LOG = LoggerFactory.getLogger(MfnContextUtils.class);

    private static ThreadLocal<Map<String, String>> map = new ThreadLocal<Map<String, String>>(){
        public Map<String, String> initialValue() {
            return new ConcurrentHashMap<>();
        }
    };

    public static void setValue(String key, String value) {
        if ("seq".equalsIgnoreCase(key)) {
            throw new RuntimeException("can't set seq with setValue, please replace with setSeq");
        }
        map.get().put(key, value);
    }

    public static String getValue(String key) {
        if ("seq".equalsIgnoreCase(key)) {
            throw new RuntimeException("can't get seq with getValue, please replace with getSeq");
        }
        return map.get().get(key);
    }

    public static String getSeq() {
        return map.get().get("seq");
    }

    public static void setSeq(String seq) {
        map.get().put("seq", seq);
    }
}
