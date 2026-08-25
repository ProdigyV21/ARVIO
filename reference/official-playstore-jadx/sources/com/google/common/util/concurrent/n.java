package com.google.common.util.concurrent;

import androidx.media3.exoplayer.upstream.CmcdData;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes4.dex */
public final class n extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Unsafe f14216a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f14217b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final long f14218c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final long f14219d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f14220e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final long f14221f;

    static {
        Unsafe unsafe;
        try {
            try {
                unsafe = Unsafe.getUnsafe();
            } catch (PrivilegedActionException e5) {
                throw new RuntimeException("Could not initialize intrinsics", e5.getCause());
            }
        } catch (SecurityException unused) {
            unsafe = (Unsafe) AccessController.doPrivileged(new m());
        }
        try {
            f14218c = unsafe.objectFieldOffset(p.class.getDeclaredField("waiters"));
            f14217b = unsafe.objectFieldOffset(p.class.getDeclaredField("listeners"));
            f14219d = unsafe.objectFieldOffset(p.class.getDeclaredField("value"));
            f14220e = unsafe.objectFieldOffset(o.class.getDeclaredField(CmcdData.OBJECT_TYPE_AUDIO_ONLY));
            f14221f = unsafe.objectFieldOffset(o.class.getDeclaredField("b"));
            f14216a = unsafe;
        } catch (NoSuchFieldException e6) {
            throw new RuntimeException(e6);
        }
    }

    @Override // com.google.common.util.concurrent.a
    public final boolean a(p pVar, d dVar, d dVar2) {
        return k.a(f14216a, pVar, f14217b, dVar, dVar2);
    }

    @Override // com.google.common.util.concurrent.a
    public final boolean b(p pVar, Object obj, Object obj2) {
        return l.a(f14216a, pVar, f14219d, obj, obj2);
    }

    @Override // com.google.common.util.concurrent.a
    public final boolean c(p pVar, o oVar, o oVar2) {
        return j.a(f14216a, pVar, f14218c, oVar, oVar2);
    }

    @Override // com.google.common.util.concurrent.a
    public final d d(p pVar) {
        d dVar;
        d dVar2;
        do {
            dVar = pVar.listeners;
            dVar2 = d.f14196d;
            if (dVar2 == dVar) {
                break;
            }
        } while (!a(pVar, dVar, dVar2));
        return dVar;
    }

    @Override // com.google.common.util.concurrent.a
    public final o e(p pVar) {
        o oVar;
        o oVar2;
        do {
            oVar = pVar.waiters;
            oVar2 = o.f14225c;
            if (oVar2 == oVar) {
                break;
            }
        } while (!c(pVar, oVar, oVar2));
        return oVar;
    }

    @Override // com.google.common.util.concurrent.a
    public final void f(o oVar, o oVar2) {
        f14216a.putObject(oVar, f14221f, oVar2);
    }

    @Override // com.google.common.util.concurrent.a
    public final void g(o oVar, Thread thread) {
        f14216a.putObject(oVar, f14220e, thread);
    }
}
