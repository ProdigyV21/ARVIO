package com.squareup.moshi;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes4.dex */
public final class d extends e0 {
    public final /* synthetic */ Method k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f14412l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Class f14413m;

    public d(Method method, Object obj, Class cls) {
        this.k = method;
        this.f14412l = obj;
        this.f14413m = cls;
    }

    @Override // com.squareup.moshi.e0
    public final Object e() {
        return this.k.invoke(this.f14412l, this.f14413m);
    }

    public final String toString() {
        return this.f14413m.getName();
    }
}
