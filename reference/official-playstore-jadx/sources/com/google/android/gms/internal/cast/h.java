package com.google.android.gms.internal.cast;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes4.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Unsafe f13266a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Class f13267b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final g f13268c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean f13269d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final boolean f13270e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final long f13271f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final boolean f13272g;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0044  */
    static {
        /*
            Method dump skipped, instruction units count: 356
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.h.<clinit>():void");
    }

    public static void a(Class cls) {
        if (f13270e) {
            f13268c.f13253a.arrayIndexScale(cls);
        }
    }

    public static Field b() {
        Field declaredField;
        Field declaredField2;
        int i10 = da.f13235a;
        try {
            declaredField = Buffer.class.getDeclaredField("effectiveDirectAddress");
        } catch (Throwable unused) {
            declaredField = null;
        }
        if (declaredField != null) {
            return declaredField;
        }
        try {
            declaredField2 = Buffer.class.getDeclaredField("address");
        } catch (Throwable unused2) {
            declaredField2 = null;
        }
        if (declaredField2 == null || declaredField2.getType() != Long.TYPE) {
            return null;
        }
        return declaredField2;
    }

    public static void c(Object obj, long j10, byte b10) {
        Unsafe unsafe = f13268c.f13253a;
        long j11 = (-4) & j10;
        int i10 = unsafe.getInt(obj, j11);
        int i11 = ((~((int) j10)) & 3) << 3;
        unsafe.putInt(obj, j11, ((255 & b10) << i11) | (i10 & (~(255 << i11))));
    }

    public static void d(Object obj, long j10, byte b10) {
        Unsafe unsafe = f13268c.f13253a;
        long j11 = (-4) & j10;
        int i10 = (((int) j10) & 3) << 3;
        unsafe.putInt(obj, j11, ((255 & b10) << i10) | (unsafe.getInt(obj, j11) & (~(255 << i10))));
    }

    public static Object e(Class cls) {
        try {
            return f13266a.allocateInstance(cls);
        } catch (InstantiationException e5) {
            throw new IllegalStateException(e5);
        }
    }

    public static int f(long j10, Object obj) {
        return f13268c.f13253a.getInt(obj, j10);
    }

    public static void g(int i10, long j10, Object obj) {
        f13268c.f13253a.putInt(obj, j10, i10);
    }

    public static long h(long j10, Object obj) {
        return f13268c.f13253a.getLong(obj, j10);
    }

    public static void i(Object obj, long j10, long j11) {
        f13268c.f13253a.putLong(obj, j10, j11);
    }

    public static Object j(long j10, Object obj) {
        return f13268c.f13253a.getObject(obj, j10);
    }

    public static void k(Object obj, Object obj2, long j10) {
        f13268c.f13253a.putObject(obj, j10, obj2);
    }

    public static Unsafe l() {
        try {
            return (Unsafe) AccessController.doPrivileged(new d());
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean m(Class cls) {
        int i10 = da.f13235a;
        try {
            Class cls2 = f13267b;
            Class cls3 = Boolean.TYPE;
            cls2.getMethod("peekLong", cls, cls3);
            cls2.getMethod("pokeLong", cls, Long.TYPE, cls3);
            Class cls4 = Integer.TYPE;
            cls2.getMethod("pokeInt", cls, cls4, cls3);
            cls2.getMethod("peekInt", cls, cls3);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, cls4, cls4);
            cls2.getMethod("peekByteArray", cls, byte[].class, cls4, cls4);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static /* synthetic */ boolean n(long j10, Object obj) {
        return ((byte) ((f13268c.f13253a.getInt(obj, (-4) & j10) >>> ((int) (((~j10) & 3) << 3))) & 255)) != 0;
    }

    public static /* synthetic */ boolean o(long j10, Object obj) {
        return ((byte) ((f13268c.f13253a.getInt(obj, (-4) & j10) >>> ((int) ((j10 & 3) << 3))) & 255)) != 0;
    }

    public static int p(Class cls) {
        if (f13270e) {
            return f13268c.f13253a.arrayBaseOffset(cls);
        }
        return -1;
    }
}
