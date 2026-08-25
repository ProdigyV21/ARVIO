package com.google.common.collect;

import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class v1 extends a1 {
    @Override // com.google.common.collect.b1
    public final b1 a(Object obj) {
        obj.getClass();
        c(obj);
        return this;
    }

    public final w1 f() {
        int i10 = this.f13928b;
        if (i10 == 0) {
            int i11 = w1.f14132m;
            return w3.f14135t;
        }
        if (i10 != 1) {
            w1 w1VarK = w1.k(i10, this.f13927a);
            this.f13928b = w1VarK.size();
            this.f13929c = true;
            return w1VarK;
        }
        Object obj = this.f13927a[0];
        Objects.requireNonNull(obj);
        int i12 = w1.f14132m;
        return new e4(obj);
    }
}
