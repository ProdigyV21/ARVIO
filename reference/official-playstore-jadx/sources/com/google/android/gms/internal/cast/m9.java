package com.google.android.gms.internal.cast;

import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes4.dex */
public abstract /* synthetic */ class m9 {
    public static /* synthetic */ boolean a(Unsafe unsafe, q9 q9Var, long j10, g9 g9Var, g9 g9Var2) {
        while (!unsafe.compareAndSwapObject(q9Var, j10, g9Var, g9Var2)) {
            if (unsafe.getObject(q9Var, j10) != g9Var) {
                return false;
            }
        }
        return true;
    }
}
