package com.google.common.util.concurrent;

/* JADX INFO: loaded from: classes4.dex */
public final class t extends t7.a {
    @Override // t7.a
    public final int o(n0 n0Var) {
        int i10;
        synchronized (n0Var) {
            i10 = n0Var.f14241l - 1;
            n0Var.f14241l = i10;
        }
        return i10;
    }
}
