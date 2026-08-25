package com.google.android.gms.internal.cast;

import j$.util.Map;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/* JADX INFO: loaded from: classes4.dex */
public abstract class l4 implements Map, Serializable, j$.util.Map {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public transient r4 f13408i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public transient s4 f13409l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public transient t4 f13410m;

    /*  JADX ERROR: Types fix failed
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [short[], byte[]], vars: [r4v4 ??, r4v10 ??, r4v5 ??, r4v8 ??, r4v6 ??, r4v7 ??, r4v9 ??, r4v12 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:48)
        	at jadx.core.dex.visitors.InitCodeVariables.rerun(InitCodeVariables.java:36)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryToFixIncompatiblePrimitives(FixTypesVisitor.java:818)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
        */
    public static void b(java.util.Set r18) {
        /*
            Method dump skipped, instruction units count: 619
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.l4.b(java.util.Set):void");
    }

    @Override // java.util.Map
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object compute(Object obj, BiFunction biFunction) {
        return Map.CC.$default$compute(this, obj, biFunction);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object computeIfAbsent(Object obj, Function function) {
        return Map.CC.$default$computeIfAbsent(this, obj, function);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object computeIfPresent(Object obj, BiFunction biFunction) {
        return Map.CC.$default$computeIfPresent(this, obj, biFunction);
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        t4 t4Var = this.f13410m;
        if (t4Var == null) {
            u4 u4Var = (u4) this;
            t4 t4Var2 = new t4(u4Var.f13626o, 1, u4Var.f13627p);
            this.f13410m = t4Var2;
            t4Var = t4Var2;
        }
        return t4Var.contains(obj);
    }

    @Override // java.util.Map
    public final Set entrySet() {
        r4 r4Var = this.f13408i;
        if (r4Var != null) {
            return r4Var;
        }
        u4 u4Var = (u4) this;
        r4 r4Var2 = new r4(u4Var, u4Var.f13626o, u4Var.f13627p);
        this.f13408i = r4Var2;
        return r4Var2;
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof java.util.Map) {
            return entrySet().equals(((java.util.Map) obj).entrySet());
        }
        return false;
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ void forEach(BiConsumer biConsumer) {
        Map.CC.$default$forEach(this, biConsumer);
    }

    @Override // java.util.Map
    public abstract Object get(Object obj);

    @Override // java.util.Map, j$.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    @Override // java.util.Map
    public final int hashCode() {
        r4 r4Var = this.f13408i;
        if (r4Var == null) {
            u4 u4Var = (u4) this;
            r4 r4Var2 = new r4(u4Var, u4Var.f13626o, u4Var.f13627p);
            this.f13408i = r4Var2;
            r4Var = r4Var2;
        }
        Iterator it = r4Var.iterator();
        int iHashCode = 0;
        while (it.hasNext()) {
            Object next = it.next();
            iHashCode += next != null ? next.hashCode() : 0;
        }
        return iHashCode;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return ((u4) this).size() == 0;
    }

    @Override // java.util.Map
    public final Set keySet() {
        s4 s4Var = this.f13409l;
        if (s4Var != null) {
            return s4Var;
        }
        u4 u4Var = (u4) this;
        s4 s4Var2 = new s4(u4Var, new t4(u4Var.f13626o, 0, u4Var.f13627p));
        this.f13409l = s4Var2;
        return s4Var2;
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object merge(Object obj, Object obj2, BiFunction biFunction) {
        return Map.CC.$default$merge(this, obj, obj2, biFunction);
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final void putAll(java.util.Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object putIfAbsent(Object obj, Object obj2) {
        return Map.CC.$default$putIfAbsent(this, obj, obj2);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ boolean remove(Object obj, Object obj2) {
        return Map.CC.$default$remove(this, obj, obj2);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ Object replace(Object obj, Object obj2) {
        return Map.CC.$default$replace(this, obj, obj2);
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ void replaceAll(BiFunction biFunction) {
        Map.CC.$default$replaceAll(this, biFunction);
    }

    public final String toString() {
        int i10 = ((u4) this).f13627p;
        if (i10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.p(i10, "size cannot be negative but was: ", new StringBuilder(String.valueOf(i10).length() + 33)));
        }
        StringBuilder sb2 = new StringBuilder((int) Math.min(((long) i10) * 8, 1073741824L));
        sb2.append('{');
        boolean z = true;
        for (Map.Entry entry : (r4) entrySet()) {
            if (!z) {
                sb2.append(", ");
            }
            sb2.append(entry.getKey());
            sb2.append('=');
            sb2.append(entry.getValue());
            z = false;
        }
        sb2.append('}');
        return sb2.toString();
    }

    @Override // java.util.Map
    public final Collection values() {
        t4 t4Var = this.f13410m;
        if (t4Var != null) {
            return t4Var;
        }
        u4 u4Var = (u4) this;
        t4 t4Var2 = new t4(u4Var.f13626o, 1, u4Var.f13627p);
        this.f13410m = t4Var2;
        return t4Var2;
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map, j$.util.Map
    public /* synthetic */ boolean replace(Object obj, Object obj2, Object obj3) {
        return Map.CC.$default$replace(this, obj, obj2, obj3);
    }
}
