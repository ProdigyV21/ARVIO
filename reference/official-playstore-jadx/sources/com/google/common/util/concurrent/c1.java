package com.google.common.util.concurrent;

import java.util.logging.Logger;

/* JADX INFO: loaded from: classes4.dex */
public final class c1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f14193a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f14194b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile Logger f14195c;

    public c1(Class cls) {
        this.f14194b = cls.getName();
    }

    public final Logger a() {
        Logger logger = this.f14195c;
        if (logger != null) {
            return logger;
        }
        synchronized (this.f14193a) {
            try {
                Logger logger2 = this.f14195c;
                if (logger2 != null) {
                    return logger2;
                }
                Logger logger3 = Logger.getLogger(this.f14194b);
                this.f14195c = logger3;
                return logger3;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
