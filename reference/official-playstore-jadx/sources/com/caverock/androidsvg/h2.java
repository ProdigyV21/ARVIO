package com.caverock.androidsvg;

import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public abstract class h2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashMap f12336a;

    static {
        HashMap map = new HashMap(9);
        f12336a = map;
        map.put("xx-small", new g0(0.694f, 7));
        map.put("x-small", new g0(0.833f, 7));
        map.put("small", new g0(10.0f, 7));
        map.put("medium", new g0(12.0f, 7));
        map.put("large", new g0(14.4f, 7));
        map.put("x-large", new g0(17.3f, 7));
        map.put("xx-large", new g0(20.7f, 7));
        map.put("smaller", new g0(83.33f, 9));
        map.put("larger", new g0(120.0f, 9));
    }
}
