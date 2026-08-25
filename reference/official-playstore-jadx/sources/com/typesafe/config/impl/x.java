package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import j$.time.Duration;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes4.dex */
public abstract class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final w1 f14641a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final i f14642b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final i f14643c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final m0 f14644d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final t1 f14645e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final v1 f14646f;

    static {
        w1 w1VarG = w1.g("hardcoded value");
        f14641a = w1VarG;
        f14642b = new i(w1VarG, true);
        f14643c = new i(w1VarG, false);
        f14644d = new m0(w1VarG);
        f14645e = new t1(Collections.EMPTY_LIST, w1VarG);
        f14646f = v1.d0(w1VarG);
    }

    public static l6.a a(ClassLoader classLoader, String str, Callable callable) {
        l6.a aVar;
        try {
            u uVar = v.f14626a;
            synchronized (uVar) {
                if (classLoader != uVar.f14623b.get()) {
                    uVar.f14624c.clear();
                    uVar.f14623b = new WeakReference(classLoader);
                }
                try {
                    p1 p1Var = w.f14631a.f14511l;
                    if (p1Var != uVar.f14622a) {
                        uVar.f14624c.clear();
                        uVar.f14622a = p1Var;
                    }
                    aVar = (l6.a) uVar.f14624c.get(str);
                    if (aVar == null) {
                        try {
                            aVar = (l6.a) callable.call();
                            if (aVar == null) {
                                throw new ConfigException.BugOrBroken(null, "null config from cache updater");
                            }
                            uVar.f14624c.put(str, aVar);
                        } catch (RuntimeException e5) {
                            throw e5;
                        } catch (Exception e6) {
                            throw new ConfigException.Generic(e6, e6.getMessage());
                        }
                    }
                } catch (ExceptionInInitializerError e10) {
                    throw y.b(e10);
                }
            }
            return aVar;
        } catch (ExceptionInInitializerError e11) {
            throw y.b(e11);
        }
    }

    public static g b(Object obj, l6.k kVar) {
        if (kVar == null) {
            throw new ConfigException.BugOrBroken(null, "origin not supposed to be null");
        }
        w1 w1Var = f14641a;
        if (obj == null) {
            return kVar != w1Var ? new m0(kVar) : f14644d;
        }
        if (obj instanceof g) {
            return (g) obj;
        }
        if (obj instanceof Boolean) {
            return kVar != w1Var ? new i(kVar, ((Boolean) obj).booleanValue()) : ((Boolean) obj).booleanValue() ? f14642b : f14643c;
        }
        if (obj instanceof String) {
            return new q0(kVar, (String) obj);
        }
        if (obj instanceof Number) {
            if (obj instanceof Double) {
                return new n(kVar, ((Double) obj).doubleValue(), null);
            }
            if (obj instanceof Integer) {
                return new z(kVar, ((Integer) obj).intValue(), null);
            }
            if (obj instanceof Long) {
                return new a0(kVar, ((Long) obj).longValue(), null);
            }
            double dDoubleValue = ((Number) obj).doubleValue();
            long j10 = (long) dDoubleValue;
            return ((double) j10) == dDoubleValue ? (j10 > 2147483647L || j10 < -2147483648L) ? new a0(kVar, j10, null) : new z(kVar, (int) j10, null) : new n(kVar, dDoubleValue, null);
        }
        if (obj instanceof Duration) {
            return new a0(kVar, ((Duration) obj).toMillis(), null);
        }
        if (!(obj instanceof Map)) {
            if (!(obj instanceof Iterable)) {
                throw new ConfigException.BugOrBroken(null, "bug in method caller: not valid to create ConfigValue from: " + obj);
            }
            Iterator it = ((Iterable) obj).iterator();
            if (!it.hasNext()) {
                return kVar == w1Var ? f14645e : new t1(Collections.EMPTY_LIST, kVar);
            }
            ArrayList arrayList = new ArrayList();
            while (it.hasNext()) {
                arrayList.add(b(it.next(), kVar));
            }
            return new t1(kVar, arrayList, com.arflix.tv.data.repository.g.a(arrayList));
        }
        Map map = (Map) obj;
        if (map.isEmpty()) {
            return kVar == w1Var ? f14646f : v1.d0(kVar);
        }
        HashMap map2 = new HashMap();
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            if (!(key instanceof String)) {
                throw new ConfigException.BugOrBroken(null, "Map has a non-string as a key, expecting a path expression as a String");
            }
            map2.put(g1.c((String) key), entry.getValue());
        }
        return k1.b(kVar, map2, false);
    }

    public static ConfigException.NotResolved c(g1 g1Var, ConfigException.NotResolved notResolved) {
        String str = g1Var.e() + " has not been resolved, you need to call Config#resolve(), see API docs for Config#resolve()";
        return str.equals(notResolved.getMessage()) ? notResolved : new ConfigException.NotResolved(notResolved, str);
    }

    public static void d(int i10, String str) {
        while (i10 > 0) {
            System.err.print("  ");
            i10--;
        }
        System.err.println(str);
    }

    public static void e(String str) {
        System.err.println(str);
    }

    public static boolean f() {
        try {
            return q.f14610a;
        } catch (ExceptionInInitializerError e5) {
            throw y.b(e5);
        }
    }

    public static boolean g() {
        try {
            return q.f14611b;
        } catch (ExceptionInInitializerError e5) {
            throw y.b(e5);
        }
    }
}
