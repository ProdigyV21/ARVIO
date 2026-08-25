package com.google.android.gms.internal.cast;

import j$.util.concurrent.ConcurrentHashMap;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public abstract class pa extends ba {
    private static final Map zzd = new ConcurrentHashMap();
    private int zzb;
    protected c zzc;

    public pa() {
        this.zza = 0;
        this.zzb = -1;
        this.zzc = c.f13198e;
    }

    public static pa h(Class cls) {
        Map map = zzd;
        pa paVar = (pa) map.get(cls);
        if (paVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                paVar = (pa) map.get(cls);
            } catch (ClassNotFoundException e5) {
                throw new IllegalStateException("Class initialization cannot fail.", e5);
            }
        }
        if (paVar != null) {
            return paVar;
        }
        pa paVar2 = (pa) ((pa) h.e(cls)).k(6, null);
        if (paVar2 == null) {
            throw new IllegalStateException();
        }
        map.put(cls, paVar2);
        return paVar2;
    }

    public static void i(Class cls, pa paVar) {
        paVar.m();
        zzd.put(cls, paVar);
    }

    public static Object j(Method method, pa paVar, Object... objArr) {
        try {
            return method.invoke(paVar, objArr);
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

    @Override // com.google.android.gms.internal.cast.jb
    public final /* synthetic */ pa a() {
        return (pa) k(6, null);
    }

    @Override // com.google.android.gms.internal.cast.ib
    public final /* synthetic */ oa b() {
        return (oa) k(5, null);
    }

    @Override // com.google.android.gms.internal.cast.ba
    public final int c(rb rbVar) {
        if (l()) {
            int iB = rbVar.b(this);
            if (iB >= 0) {
                return iB;
            }
            StringBuilder sb2 = new StringBuilder(String.valueOf(iB).length() + 42);
            sb2.append("serialized size must be non-negative, was ");
            sb2.append(iB);
            throw new IllegalStateException(sb2.toString());
        }
        int i10 = this.zzb & Integer.MAX_VALUE;
        if (i10 != Integer.MAX_VALUE) {
            return i10;
        }
        int iB2 = rbVar.b(this);
        if (iB2 >= 0) {
            this.zzb = (this.zzb & Integer.MIN_VALUE) | iB2;
            return iB2;
        }
        StringBuilder sb3 = new StringBuilder(String.valueOf(iB2).length() + 42);
        sb3.append("serialized size must be non-negative, was ");
        sb3.append(iB2);
        throw new IllegalStateException(sb3.toString());
    }

    public final oa e() {
        return (oa) k(5, null);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return ob.f13531c.a(getClass()).a(this, (pa) obj);
    }

    public final void f() {
        this.zzb = (this.zzb & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    public final int g() {
        if (l()) {
            int iB = ob.f13531c.a(getClass()).b(this);
            if (iB >= 0) {
                return iB;
            }
            StringBuilder sb2 = new StringBuilder(String.valueOf(iB).length() + 42);
            sb2.append("serialized size must be non-negative, was ");
            sb2.append(iB);
            throw new IllegalStateException(sb2.toString());
        }
        int i10 = this.zzb & Integer.MAX_VALUE;
        if (i10 != Integer.MAX_VALUE) {
            return i10;
        }
        int iB2 = ob.f13531c.a(getClass()).b(this);
        if (iB2 >= 0) {
            this.zzb = (this.zzb & Integer.MIN_VALUE) | iB2;
            return iB2;
        }
        StringBuilder sb3 = new StringBuilder(String.valueOf(iB2).length() + 42);
        sb3.append("serialized size must be non-negative, was ");
        sb3.append(iB2);
        throw new IllegalStateException(sb3.toString());
    }

    public final int hashCode() {
        if (l()) {
            return ob.f13531c.a(getClass()).d(this);
        }
        int i10 = this.zza;
        if (i10 != 0) {
            return i10;
        }
        int iD = ob.f13531c.a(getClass()).d(this);
        this.zza = iD;
        return iD;
    }

    public abstract Object k(int i10, pa paVar);

    public final boolean l() {
        return (this.zzb & Integer.MIN_VALUE) != 0;
    }

    public final void m() {
        this.zzb &= Integer.MAX_VALUE;
    }

    public final String toString() {
        String string = super.toString();
        char[] cArr = kb.f13391a;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("# ");
        sb2.append(string);
        kb.b(this, sb2, 0);
        return sb2.toString();
    }
}
