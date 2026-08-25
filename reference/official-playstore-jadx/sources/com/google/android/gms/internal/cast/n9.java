package com.google.android.gms.internal.cast;

import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes4.dex */
public abstract /* synthetic */ class n9 {
    public static /* synthetic */ boolean a(Unsafe unsafe, q9 q9Var, long j10, p9 p9Var, p9 p9Var2) {
        while (!unsafe.compareAndSwapObject(q9Var, j10, p9Var, p9Var2)) {
            if (unsafe.getObject(q9Var, j10) != p9Var) {
                return false;
            }
        }
        return true;
    }
}
