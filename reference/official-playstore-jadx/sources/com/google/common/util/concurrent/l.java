package com.google.common.util.concurrent;

import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes4.dex */
public abstract /* synthetic */ class l {
    public static /* synthetic */ boolean a(Unsafe unsafe, p pVar, long j10, Object obj, Object obj2) {
        while (!unsafe.compareAndSwapObject(pVar, j10, obj, obj2)) {
            if (unsafe.getObject(pVar, j10) != obj) {
                return false;
            }
        }
        return true;
    }
}
