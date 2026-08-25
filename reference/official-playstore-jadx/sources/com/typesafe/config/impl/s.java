package com.typesafe.config.impl;

/* JADX INFO: loaded from: classes4.dex */
public abstract class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile v1 f14616a;

    static {
        w1 w1Var = x.f14641a;
        f14616a = k1.a(w1.g("env variables"), System.getenv().entrySet());
    }
}
