package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.net.MalformedURLException;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes4.dex */
public final class o0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final v0 f14598b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final l6.b f14599c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f14600d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final l6.k f14601e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f14597a = 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final LinkedList f14602f = new LinkedList();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f14603g = 0;

    public o0(int i10, l6.k kVar, j0 j0Var, v0 v0Var, io.sentry.internal.debugmeta.c cVar) {
        this.f14600d = i10;
        this.f14601e = kVar;
        this.f14598b = v0Var;
        this.f14599c = cVar;
    }

    public final ConfigException.Parse a(String str, MalformedURLException malformedURLException) {
        return new ConfigException.Parse(((w1) this.f14601e).b(this.f14597a), str, malformedURLException);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00c8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.typesafe.config.impl.g b(com.typesafe.config.impl.b r20, java.util.ArrayList r21) {
        /*
            Method dump skipped, instruction units count: 1337
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.impl.o0.b(com.typesafe.config.impl.b, java.util.ArrayList):com.typesafe.config.impl.g");
    }
}
