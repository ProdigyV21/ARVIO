package com.google.common.util.concurrent;

import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes4.dex */
public abstract /* synthetic */ class k {
    public static /* synthetic */ boolean a(Unsafe unsafe, p pVar, long j10, d dVar, d dVar2) {
        while (!unsafe.compareAndSwapObject(pVar, j10, dVar, dVar2)) {
            if (unsafe.getObject(pVar, j10) != dVar) {
                return false;
            }
        }
        return true;
    }
}
