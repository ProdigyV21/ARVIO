package com.google.android.gms.internal.cast;

import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes4.dex */
public abstract /* synthetic */ class l9 {
    public static /* synthetic */ boolean a(Unsafe unsafe, q9 q9Var, long j10, Object obj, Object obj2) {
        while (!unsafe.compareAndSwapObject(q9Var, j10, obj, obj2)) {
            if (unsafe.getObject(q9Var, j10) != obj) {
                return false;
            }
        }
        return true;
    }
}
