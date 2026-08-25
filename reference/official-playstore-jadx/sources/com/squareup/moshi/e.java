package com.squareup.moshi;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes4.dex */
public final class e extends e0 {
    public final /* synthetic */ Method k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Class f14420l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f14421m;

    public e(Method method, Class cls, int i10) {
        this.k = method;
        this.f14420l = cls;
        this.f14421m = i10;
    }

    @Override // com.squareup.moshi.e0
    public final Object e() {
        return this.k.invoke(null, this.f14420l, Integer.valueOf(this.f14421m));
    }

    public final String toString() {
        return this.f14420l.getName();
    }
}
