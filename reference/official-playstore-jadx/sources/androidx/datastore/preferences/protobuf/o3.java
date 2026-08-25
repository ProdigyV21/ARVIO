package androidx.datastore.preferences.protobuf;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes3.dex */
public abstract class o3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Unsafe f2486a;

    public o3(Unsafe unsafe) {
        this.f2486a = unsafe;
    }

    public final int a(Class cls) {
        return this.f2486a.arrayBaseOffset(cls);
    }

    public final int b(Class cls) {
        return this.f2486a.arrayIndexScale(cls);
    }

    public abstract boolean c(long j10, Object obj);

    public abstract byte d(long j10, Object obj);

    public abstract double e(long j10, Object obj);

    public abstract float f(long j10, Object obj);

    public final int g(long j10, Object obj) {
        return this.f2486a.getInt(obj, j10);
    }

    public final long h(long j10, Object obj) {
        return this.f2486a.getLong(obj, j10);
    }

    public final Object i(long j10, Object obj) {
        return this.f2486a.getObject(obj, j10);
    }

    public final long j(Field field) {
        return this.f2486a.objectFieldOffset(field);
    }

    public abstract void k(Object obj, long j10, boolean z);

    public abstract void l(Object obj, long j10, byte b10);

    public abstract void m(Object obj, long j10, double d4);

    public abstract void n(Object obj, long j10, float f10);

    public final void o(int i10, long j10, Object obj) {
        this.f2486a.putInt(obj, j10, i10);
    }

    public final void p(Object obj, long j10, long j11) {
        this.f2486a.putLong(obj, j10, j11);
    }

    public final void q(Object obj, Object obj2, long j10) {
        this.f2486a.putObject(obj, j10, obj2);
    }
}
