package io.sentry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class j0 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final HashMap f17389h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap f17390a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f17391b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final io.sentry.util.b f17392c = new io.sentry.util.b();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a f17393d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f17394e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a f17395f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public y3 f17396g = null;

    static {
        HashMap map = new HashMap();
        f17389h = map;
        map.put("boolean", Boolean.class);
        map.put("char", Character.class);
        map.put("byte", Byte.class);
        map.put("short", Short.class);
        map.put("int", Integer.class);
        map.put("long", Long.class);
        map.put("float", Float.class);
        map.put("double", Double.class);
    }

    public final void a() {
        io.sentry.util.a aVarA = this.f17392c.a();
        try {
            Iterator it = this.f17390a.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (entry.getKey() == null || !((String) entry.getKey()).startsWith("sentry:")) {
                    it.remove();
                }
            }
            aVarA.close();
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final Object b(String str) {
        io.sentry.util.a aVarA = this.f17392c.a();
        try {
            Object obj = this.f17390a.get(str);
            aVarA.close();
            return obj;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final Object c(Class cls, String str) {
        io.sentry.util.a aVarA = this.f17392c.a();
        try {
            Object obj = this.f17390a.get(str);
            if (cls.isInstance(obj)) {
                aVarA.close();
                return obj;
            }
            Class cls2 = (Class) f17389h.get(cls.getCanonicalName());
            if (obj != null && cls.isPrimitive() && cls2 != null) {
                if (cls2.isInstance(obj)) {
                    aVarA.close();
                    return obj;
                }
            }
            aVarA.close();
            return null;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final void d(String str, Object obj) {
        io.sentry.util.a aVarA = this.f17392c.a();
        try {
            this.f17390a.put(str, obj);
            aVarA.close();
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
