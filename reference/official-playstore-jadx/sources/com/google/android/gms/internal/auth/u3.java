package com.google.android.gms.internal.auth;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes4.dex */
public abstract class u3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Unsafe f13128a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Class f13129b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final t3 f13130c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean f13131d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final boolean f13132e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final boolean f13133f;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0044  */
    static {
        /*
            Method dump skipped, instruction units count: 352
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.u3.<clinit>():void");
    }

    public static int a(long j10, Object obj) {
        return f13130c.f13125a.getInt(obj, j10);
    }

    public static long b(long j10, Object obj) {
        return f13130c.f13125a.getLong(obj, j10);
    }

    public static Object c(Class cls) {
        try {
            return f13128a.allocateInstance(cls);
        } catch (InstantiationException e5) {
            throw new IllegalStateException(e5);
        }
    }

    public static Object d(long j10, Object obj) {
        return f13130c.f13125a.getObject(obj, j10);
    }

    public static Unsafe e() {
        try {
            return (Unsafe) AccessController.doPrivileged(new q3());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static /* synthetic */ void f(Object obj, long j10, boolean z) {
        t3 t3Var = f13130c;
        long j11 = (-4) & j10;
        int i10 = t3Var.f13125a.getInt(obj, j11);
        int i11 = ((~((int) j10)) & 3) << 3;
        t3Var.f13125a.putInt(obj, j11, ((z ? 1 : 0) << i11) | ((~(255 << i11)) & i10));
    }

    public static /* synthetic */ void g(Object obj, long j10, boolean z) {
        t3 t3Var = f13130c;
        long j11 = (-4) & j10;
        int i10 = (((int) j10) & 3) << 3;
        t3Var.f13125a.putInt(obj, j11, ((z ? 1 : 0) << i10) | ((~(255 << i10)) & t3Var.f13125a.getInt(obj, j11)));
    }

    public static void h(int i10, long j10, Object obj) {
        f13130c.f13125a.putInt(obj, j10, i10);
    }

    public static void i(Object obj, long j10, long j11) {
        f13130c.f13125a.putLong(obj, j10, j11);
    }

    public static void j(Object obj, Object obj2, long j10) {
        f13130c.f13125a.putObject(obj, j10, obj2);
    }

    public static /* bridge */ /* synthetic */ boolean k(long j10, Object obj) {
        return ((byte) ((f13130c.f13125a.getInt(obj, (-4) & j10) >>> ((int) (((~j10) & 3) << 3))) & 255)) != 0;
    }

    public static /* bridge */ /* synthetic */ boolean l(long j10, Object obj) {
        return ((byte) ((f13130c.f13125a.getInt(obj, (-4) & j10) >>> ((int) ((j10 & 3) << 3))) & 255)) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean m(Class cls) {
        int i10 = r1.f13108a;
        try {
            Class cls2 = f13129b;
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

    public static void n(Class cls) {
        if (f13132e) {
            f13130c.f13125a.arrayBaseOffset(cls);
        }
    }

    public static void o(Class cls) {
        if (f13132e) {
            f13130c.f13125a.arrayIndexScale(cls);
        }
    }

    public static Field p() {
        Field declaredField;
        Field declaredField2;
        int i10 = r1.f13108a;
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
}
