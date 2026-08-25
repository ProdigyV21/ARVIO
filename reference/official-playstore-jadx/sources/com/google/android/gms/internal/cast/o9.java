package com.google.android.gms.internal.cast;

import androidx.media3.exoplayer.upstream.CmcdData;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes4.dex */
public final class o9 extends u0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Unsafe f13523m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final long f13524n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final long f13525o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final long f13526p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final long f13527q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final long f13528r;

    static {
        Unsafe unsafe;
        try {
            try {
                unsafe = Unsafe.getUnsafe();
            } catch (SecurityException unused) {
                unsafe = (Unsafe) AccessController.doPrivileged(r9.f13591a);
            }
            try {
                f13525o = unsafe.objectFieldOffset(q9.class.getDeclaredField("o"));
                f13524n = unsafe.objectFieldOffset(q9.class.getDeclaredField("n"));
                f13526p = unsafe.objectFieldOffset(q9.class.getDeclaredField(CmcdData.OBJECT_TYPE_MANIFEST));
                f13527q = unsafe.objectFieldOffset(p9.class.getDeclaredField(CmcdData.OBJECT_TYPE_AUDIO_ONLY));
                f13528r = unsafe.objectFieldOffset(p9.class.getDeclaredField("b"));
                f13523m = unsafe;
            } catch (NoSuchFieldException e5) {
                throw new RuntimeException(e5);
            }
        } catch (PrivilegedActionException e6) {
            throw new RuntimeException("Could not initialize intrinsics", e6.getCause());
        }
    }

    @Override // com.google.android.gms.internal.cast.u0
    public final g9 A(i9 i9Var) {
        g9 g9Var;
        g9 g9Var2 = g9.f13262d;
        do {
            g9Var = i9Var.f13560n;
            if (g9Var2 == g9Var) {
                break;
            }
        } while (!x(i9Var, g9Var, g9Var2));
        return g9Var;
    }

    @Override // com.google.android.gms.internal.cast.u0
    public final boolean B(q9 q9Var, Object obj, Object obj2) {
        return l9.a(f13523m, q9Var, f13526p, obj, obj2);
    }

    @Override // com.google.android.gms.internal.cast.u0
    public final void o(p9 p9Var, Thread thread) {
        f13523m.putObject(p9Var, f13527q, thread);
    }

    @Override // com.google.android.gms.internal.cast.u0
    public final void t(p9 p9Var, p9 p9Var2) {
        f13523m.putObject(p9Var, f13528r, p9Var2);
    }

    @Override // com.google.android.gms.internal.cast.u0
    public final boolean v(q9 q9Var, p9 p9Var, p9 p9Var2) {
        return n9.a(f13523m, q9Var, f13525o, p9Var, p9Var2);
    }

    @Override // com.google.android.gms.internal.cast.u0
    public final boolean x(q9 q9Var, g9 g9Var, g9 g9Var2) {
        return m9.a(f13523m, q9Var, f13524n, g9Var, g9Var2);
    }

    @Override // com.google.android.gms.internal.cast.u0
    public final p9 y(i9 i9Var) {
        p9 p9Var;
        p9 p9Var2 = p9.f13543c;
        do {
            p9Var = i9Var.f13561o;
            if (p9Var2 == p9Var) {
                break;
            }
        } while (!v(i9Var, p9Var, p9Var2));
        return p9Var;
    }
}
