package kotlin.jvm.internal;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public abstract class q0 {
    public static Collection a(Object obj) {
        if ((obj instanceof s7.a) && !(obj instanceof s7.b)) {
            h(obj, "kotlin.collections.MutableCollection");
            throw null;
        }
        try {
            return (Collection) obj;
        } catch (ClassCastException e5) {
            p.e(e5, q0.class.getName());
            throw e5;
        }
    }

    public static List b(Object obj) {
        if ((obj instanceof s7.a) && !(obj instanceof s7.e)) {
            h(obj, "kotlin.collections.MutableList");
            throw null;
        }
        try {
            return (List) obj;
        } catch (ClassCastException e5) {
            p.e(e5, q0.class.getName());
            throw e5;
        }
    }

    public static Map c(Object obj) {
        if ((obj instanceof s7.a) && !(obj instanceof s7.g)) {
            h(obj, "kotlin.collections.MutableMap");
            throw null;
        }
        try {
            return (Map) obj;
        } catch (ClassCastException e5) {
            p.e(e5, q0.class.getName());
            throw e5;
        }
    }

    public static Set d(Object obj) {
        if ((obj instanceof s7.a) && !(obj instanceof s7.h)) {
            h(obj, "kotlin.collections.MutableSet");
            throw null;
        }
        try {
            return (Set) obj;
        } catch (ClassCastException e5) {
            p.e(e5, q0.class.getName());
            throw e5;
        }
    }

    public static void e(int i10, Object obj) {
        if (obj == null || f(i10, obj)) {
            return;
        }
        h(obj, "kotlin.jvm.functions.Function" + i10);
        throw null;
    }

    public static boolean f(int i10, Object obj) {
        if (obj instanceof x6.o) {
            if ((obj instanceof k ? ((k) obj).getArity() : obj instanceof r7.a ? 0 : obj instanceof r7.l ? 1 : obj instanceof r7.p ? 2 : obj instanceof r7.q ? 3 : obj instanceof r7.r ? 4 : obj instanceof r7.s ? 5 : obj instanceof r7.t ? 6 : obj instanceof r7.u ? 7 : obj instanceof r7.v ? 8 : obj instanceof r7.w ? 9 : obj instanceof r7.b ? 10 : obj instanceof r7.c ? 11 : obj instanceof r7.d ? 12 : obj instanceof r7.e ? 13 : obj instanceof r7.f ? 14 : obj instanceof r7.g ? 15 : obj instanceof r7.h ? 16 : obj instanceof r7.i ? 17 : obj instanceof r7.j ? 18 : obj instanceof r7.k ? 19 : obj instanceof r7.m ? 20 : obj instanceof r7.n ? 21 : obj instanceof r7.o ? 22 : -1) == i10) {
                return true;
            }
        }
        return false;
    }

    public static boolean g(Object obj) {
        if (obj instanceof List) {
            return !(obj instanceof s7.a) || (obj instanceof s7.e);
        }
        return false;
    }

    public static void h(Object obj, String str) {
        ClassCastException classCastException = new ClassCastException(androidx.compose.foundation.c.t(obj == null ? "null" : obj.getClass().getName(), " cannot be cast to ", str));
        p.e(classCastException, q0.class.getName());
        throw classCastException;
    }
}
