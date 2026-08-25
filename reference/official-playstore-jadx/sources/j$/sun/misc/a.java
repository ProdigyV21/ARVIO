package j$.sun.misc;

import j$.util.concurrent.l;
import j$.util.concurrent.q;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f18086b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Unsafe f18087a;

    static {
        Field fieldG = g();
        fieldG.setAccessible(true);
        try {
            f18086b = new a((Unsafe) fieldG.get(null));
        } catch (IllegalAccessException e5) {
            throw new AssertionError("Couldn't get the Unsafe", e5);
        }
    }

    public a(Unsafe unsafe) {
        this.f18087a = unsafe;
    }

    public static Field g() {
        try {
            return Unsafe.class.getDeclaredField("theUnsafe");
        } catch (NoSuchFieldException e5) {
            for (Field field : Unsafe.class.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers()) && Unsafe.class.isAssignableFrom(field.getType())) {
                    return field;
                }
            }
            throw new AssertionError("Couldn't find the Unsafe", e5);
        }
    }

    public final int e(q qVar, long j10) {
        while (true) {
            int intVolatile = this.f18087a.getIntVolatile(qVar, j10);
            q qVar2 = qVar;
            long j11 = j10;
            if (this.f18087a.compareAndSwapInt(qVar2, j11, intVolatile, intVolatile - 4)) {
                return intVolatile;
            }
            qVar = qVar2;
            j10 = j11;
        }
    }

    public final long i(Field field) {
        return this.f18087a.objectFieldOffset(field);
    }

    public final long h(Class cls, String str) {
        try {
            return i(cls.getDeclaredField(str));
        } catch (NoSuchFieldException e5) {
            throw new AssertionError("Cannot find field:", e5);
        }
    }

    public final int a(Class cls) {
        return this.f18087a.arrayBaseOffset(cls);
    }

    public final int b(Class cls) {
        return this.f18087a.arrayIndexScale(cls);
    }

    public final Object f(Object obj, long j10) {
        return this.f18087a.getObjectVolatile(obj, j10);
    }

    public final void j(Object obj, long j10, l lVar) {
        this.f18087a.putObjectVolatile(obj, j10, lVar);
    }

    public final boolean c(Object obj, long j10, int i10, int i11) {
        return this.f18087a.compareAndSwapInt(obj, j10, i10, i11);
    }

    public final boolean d(Object obj, long j10, long j11, long j12) {
        return this.f18087a.compareAndSwapLong(obj, j10, j11, j12);
    }
}
