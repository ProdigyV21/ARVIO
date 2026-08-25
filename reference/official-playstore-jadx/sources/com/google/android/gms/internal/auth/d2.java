package com.google.android.gms.internal.auth;

import j$.util.concurrent.ConcurrentHashMap;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public abstract class d2 extends p1 {
    private static final Map zzb = new ConcurrentHashMap();
    protected l3 zzc;
    private int zzd;

    public d2() {
        this.zza = 0;
        this.zzd = -1;
        this.zzc = l3.f13065e;
    }

    public static d2 a(Class cls) {
        Map map = zzb;
        d2 d2Var = (d2) map.get(cls);
        if (d2Var == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                d2Var = (d2) map.get(cls);
            } catch (ClassNotFoundException e5) {
                throw new IllegalStateException("Class initialization cannot fail.", e5);
            }
        }
        if (d2Var != null) {
            return d2Var;
        }
        d2 d2Var2 = (d2) ((d2) u3.c(cls)).h(6);
        if (d2Var2 == null) {
            throw new IllegalStateException();
        }
        map.put(cls, d2Var2);
        return d2Var2;
    }

    public static Object c(Method method, d2 d2Var, Object... objArr) {
        try {
            return method.invoke(d2Var, objArr);
        } catch (IllegalAccessException e5) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e5);
        } catch (InvocationTargetException e6) {
            Throwable cause = e6.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    public static void e(x3 x3Var) {
        x3Var.d();
        zzb.put(x3.class, x3Var);
    }

    public final d2 b() {
        return (d2) h(4);
    }

    public final void d() {
        this.zzd &= Integer.MAX_VALUE;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return f3.f13029c.a(getClass()).b(this, (d2) obj);
    }

    public final void f() {
        this.zzd = (this.zzd & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    public final boolean g() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }

    public abstract Object h(int i10);

    public final int hashCode() {
        if (g()) {
            return f3.f13029c.a(getClass()).c(this);
        }
        int i10 = this.zza;
        if (i10 != 0) {
            return i10;
        }
        int iC = f3.f13029c.a(getClass()).c(this);
        this.zza = iC;
        return iC;
    }

    public final String toString() {
        String string = super.toString();
        char[] cArr = y2.f13150a;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("# ");
        sb2.append(string);
        y2.c(this, sb2, 0);
        return sb2.toString();
    }

    @Override // com.google.android.gms.internal.auth.x2
    public final /* synthetic */ d2 zze() {
        return (d2) h(6);
    }
}
