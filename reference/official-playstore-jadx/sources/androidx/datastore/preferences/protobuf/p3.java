package androidx.datastore.preferences.protobuf;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes3.dex */
public abstract class p3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f2497a = Logger.getLogger(p3.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Unsafe f2498b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Class f2499c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final o3 f2500d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final boolean f2501e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final boolean f2502f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final long f2503g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final boolean f2504h;

    /* JADX WARN: Removed duplicated region for block: B:69:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x026a  */
    static {
        /*
            Method dump skipped, instruction units count: 623
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.p3.<clinit>():void");
    }

    public static Object a(Class cls) {
        try {
            return f2498b.allocateInstance(cls);
        } catch (InstantiationException e5) {
            throw new IllegalStateException(e5);
        }
    }

    public static int b(Class cls) {
        if (f2502f) {
            return f2500d.a(cls);
        }
        return -1;
    }

    public static void c(Class cls) {
        if (f2502f) {
            f2500d.b(cls);
        }
    }

    public static Field d() {
        Field declaredField;
        Field declaredField2;
        if (e.a()) {
            try {
                declaredField2 = Buffer.class.getDeclaredField("effectiveDirectAddress");
            } catch (Throwable unused) {
                declaredField2 = null;
            }
            if (declaredField2 != null) {
                return declaredField2;
            }
        }
        try {
            declaredField = Buffer.class.getDeclaredField("address");
        } catch (Throwable unused2) {
            declaredField = null;
        }
        if (declaredField == null || declaredField.getType() != Long.TYPE) {
            return null;
        }
        return declaredField;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean e(Class cls) {
        if (!e.a()) {
            return false;
        }
        try {
            Class cls2 = f2499c;
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

    public static byte f(byte[] bArr, long j10) {
        return f2500d.d(f2503g + j10, bArr);
    }

    public static byte g(long j10, Object obj) {
        return (byte) ((f2500d.g((-4) & j10, obj) >>> ((int) (((~j10) & 3) << 3))) & 255);
    }

    public static byte h(long j10, Object obj) {
        return (byte) ((f2500d.g((-4) & j10, obj) >>> ((int) ((j10 & 3) << 3))) & 255);
    }

    public static Unsafe i() {
        try {
            return (Unsafe) AccessController.doPrivileged(new l3());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void j(byte[] bArr, long j10, byte b10) {
        f2500d.l(bArr, f2503g + j10, b10);
    }

    public static void k(Object obj, long j10, byte b10) {
        long j11 = (-4) & j10;
        int iG = f2500d.g(j11, obj);
        int i10 = ((~((int) j10)) & 3) << 3;
        m(((255 & b10) << i10) | (iG & (~(255 << i10))), j11, obj);
    }

    public static void l(Object obj, long j10, byte b10) {
        long j11 = (-4) & j10;
        int i10 = (((int) j10) & 3) << 3;
        m(((255 & b10) << i10) | (f2500d.g(j11, obj) & (~(255 << i10))), j11, obj);
    }

    public static void m(int i10, long j10, Object obj) {
        f2500d.o(i10, j10, obj);
    }

    public static void n(Object obj, long j10, long j11) {
        f2500d.p(obj, j10, j11);
    }

    public static void o(Object obj, Object obj2, long j10) {
        f2500d.q(obj, obj2, j10);
    }
}
