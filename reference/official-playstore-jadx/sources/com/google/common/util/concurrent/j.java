package com.google.common.util.concurrent;

import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes4.dex */
public abstract /* synthetic */ class j {
    public static /* synthetic */ boolean a(Unsafe unsafe, p pVar, long j10, o oVar, o oVar2) {
        while (!unsafe.compareAndSwapObject(pVar, j10, oVar, oVar2)) {
            if (unsafe.getObject(pVar, j10) != oVar) {
                return false;
            }
        }
        return true;
    }
}
