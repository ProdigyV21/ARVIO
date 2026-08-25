package com.typesafe.config.impl;

import java.util.Map;
import java.util.Properties;

/* JADX INFO: loaded from: classes4.dex */
public abstract class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile c f14631a;

    static {
        w1 w1Var = x.f14641a;
        Properties properties = System.getProperties();
        Properties properties2 = new Properties();
        synchronized (properties) {
            try {
                for (Map.Entry entry : properties.entrySet()) {
                    if (!entry.getKey().toString().startsWith("java.version.")) {
                        properties2.put(entry.getKey(), entry.getValue());
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        l6.l lVarD = new l6.l(0, null, true, null, null).d("system properties");
        com.google.android.gms.common.api.internal.f1 f1Var = f1.f14531d;
        f14631a = new a1(properties2, lVarD).j();
    }
}
