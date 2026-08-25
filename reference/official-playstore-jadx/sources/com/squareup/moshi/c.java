package com.squareup.moshi;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes4.dex */
public final class c extends e0 {
    public final /* synthetic */ int k = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Class f14405l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ AccessibleObject f14406m;

    public c(Constructor constructor, Class cls) {
        this.f14406m = constructor;
        this.f14405l = cls;
    }

    @Override // com.squareup.moshi.e0
    public final Object e() {
        int i10 = this.k;
        AccessibleObject accessibleObject = this.f14406m;
        switch (i10) {
            case 0:
                return ((Constructor) accessibleObject).newInstance(null);
            default:
                return ((Method) accessibleObject).invoke(null, this.f14405l, Object.class);
        }
    }

    public final String toString() {
        switch (this.k) {
        }
        return this.f14405l.getName();
    }

    public c(Method method, Class cls) {
        this.f14406m = method;
        this.f14405l = cls;
    }
}
