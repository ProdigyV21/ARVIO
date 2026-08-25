package com.google.android.gms.internal.auth;

/* JADX INFO: loaded from: classes4.dex */
public final class o2 implements u2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u2[] f13085a;

    public o2(u2... u2VarArr) {
        this.f13085a = u2VarArr;
    }

    @Override // com.google.android.gms.internal.auth.u2
    public final t2 a(Class cls) {
        for (int i10 = 0; i10 < 2; i10++) {
            u2 u2Var = this.f13085a[i10];
            if (u2Var.b(cls)) {
                return u2Var.a(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(cls.getName()));
    }

    @Override // com.google.android.gms.internal.auth.u2
    public final boolean b(Class cls) {
        for (int i10 = 0; i10 < 2; i10++) {
            if (this.f13085a[i10].b(cls)) {
                return true;
            }
        }
        return false;
    }
}
