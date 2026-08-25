package com.typesafe.config.impl;

import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public abstract class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f14610a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final boolean f14611b;

    static {
        HashMap map = new HashMap();
        Boolean bool = Boolean.FALSE;
        map.put("loads", bool);
        map.put("substitutions", bool);
        String property = System.getProperty("config.trace");
        if (property != null) {
            for (String str : property.split(",")) {
                if (str.equals("loads")) {
                    map.put("loads", Boolean.TRUE);
                } else if (str.equals("substitutions")) {
                    map.put("substitutions", Boolean.TRUE);
                } else {
                    System.err.println("config.trace property contains unknown trace topic '" + str + "'");
                }
            }
        }
        f14610a = ((Boolean) map.get("loads")).booleanValue();
        f14611b = ((Boolean) map.get("substitutions")).booleanValue();
    }
}
