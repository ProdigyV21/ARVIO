package com.google.common.collect;

/* JADX INFO: loaded from: classes4.dex */
public final class e3 extends c3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f13979a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f13980b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ f3 f13981c;

    public e3(f3 f3Var, int i10) {
        this.f13981c = f3Var;
        this.f13979a = f3Var.f13998a[i10];
        this.f13980b = i10;
    }

    @Override // com.google.common.collect.b3.a
    public final Object a() {
        return this.f13979a;
    }

    @Override // com.google.common.collect.b3.a
    public final int getCount() {
        int i10 = this.f13980b;
        Object obj = this.f13979a;
        f3 f3Var = this.f13981c;
        if (i10 == -1 || i10 >= f3Var.f14000c || !a.a.L(obj, f3Var.f13998a[i10])) {
            this.f13980b = f3Var.c(obj);
        }
        int i11 = this.f13980b;
        if (i11 == -1) {
            return 0;
        }
        return f3Var.f13999b[i11];
    }
}
