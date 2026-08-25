package com.google.common.collect;

import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class e0 extends r {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f13974i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f13975l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ f0 f13976m;

    public e0(f0 f0Var, int i10) {
        this.f13976m = f0Var;
        Object obj = f0.f13987t;
        this.f13974i = f0Var.m()[i10];
        this.f13975l = i10;
    }

    public final void a() {
        int i10 = this.f13975l;
        Object obj = this.f13974i;
        f0 f0Var = this.f13976m;
        if (i10 != -1 && i10 < f0Var.size()) {
            if (a.a.L(obj, f0Var.m()[this.f13975l])) {
                return;
            }
        }
        Object obj2 = f0.f13987t;
        this.f13975l = f0Var.h(obj);
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.f13974i;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        f0 f0Var = this.f13976m;
        Map mapE = f0Var.e();
        if (mapE != null) {
            return mapE.get(this.f13974i);
        }
        a();
        int i10 = this.f13975l;
        if (i10 == -1) {
            return null;
        }
        return f0Var.o()[i10];
    }

    @Override // com.google.common.collect.r, java.util.Map.Entry
    public final Object setValue(Object obj) {
        f0 f0Var = this.f13976m;
        Map mapE = f0Var.e();
        Object obj2 = this.f13974i;
        if (mapE != null) {
            return mapE.put(obj2, obj);
        }
        a();
        int i10 = this.f13975l;
        if (i10 == -1) {
            f0Var.put(obj2, obj);
            return null;
        }
        Object obj3 = f0Var.o()[i10];
        f0Var.o()[this.f13975l] = obj;
        return obj3;
    }
}
