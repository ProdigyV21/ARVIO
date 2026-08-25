package com.google.android.gms.internal.auth;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.util.Log;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public final class y0 implements x0, w0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static y0 f13145m;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f13146i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f13147l;

    public y0() {
        this.f13146i = null;
        this.f13147l = null;
    }

    public static y0 a(Context context) {
        y0 y0Var;
        synchronized (y0.class) {
            try {
                if (f13145m == null) {
                    f13145m = qb.l.e(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new y0(context) : new y0();
                }
                y0Var = f13145m;
            } catch (Throwable th) {
                throw th;
            }
        }
        return y0Var;
    }

    public static synchronized void d() {
        Context context;
        try {
            y0 y0Var = f13145m;
            if (y0Var != null && (context = (Context) y0Var.f13146i) != null && ((p0) y0Var.f13147l) != null) {
                context.getContentResolver().unregisterContentObserver((p0) f13145m.f13147l);
            }
            f13145m = null;
        } catch (Throwable th) {
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Object b() {
        y0 y0Var = (y0) this.f13146i;
        String str = (String) this.f13147l;
        ContentResolver contentResolver = ((Context) y0Var.f13146i).getContentResolver();
        Uri uri = q0.f13093a;
        synchronized (q0.class) {
            try {
                int i10 = 0;
                String str2 = null;
                Object[] objArr = 0;
                if (q0.f13097e == null) {
                    q0.f13096d.set(false);
                    q0.f13097e = new HashMap(16, 1.0f);
                    q0.f13102j = new Object();
                    contentResolver.registerContentObserver(q0.f13093a, true, new p0(i10, objArr == true ? 1 : 0));
                } else if (q0.f13096d.getAndSet(false)) {
                    q0.f13097e.clear();
                    q0.f13098f.clear();
                    q0.f13099g.clear();
                    q0.f13100h.clear();
                    q0.f13101i.clear();
                    q0.f13102j = new Object();
                }
                Object obj = q0.f13102j;
                if (q0.f13097e.containsKey(str)) {
                    String str3 = (String) q0.f13097e.get(str);
                    if (str3 != null) {
                        str2 = str3;
                    }
                    return str2;
                }
                int length = q0.k.length;
                Cursor cursorQuery = contentResolver.query(q0.f13093a, null, null, new String[]{str}, null);
                if (cursorQuery != null) {
                    try {
                        if (!cursorQuery.moveToFirst()) {
                            q0.a(str, obj, null);
                            return null;
                        }
                        String string = cursorQuery.getString(1);
                        if (string != null && string.equals(null)) {
                            string = null;
                        }
                        q0.a(str, obj, string);
                        if (string != null) {
                            return string;
                        }
                    } finally {
                        cursorQuery.close();
                    }
                }
                return null;
            } finally {
            }
        }
    }

    public String c(String str) {
        Object objB;
        Context context = (Context) this.f13146i;
        if (context != null && !r0.a(context)) {
            try {
                try {
                    y0 y0Var = new y0(this, str);
                    try {
                        objB = y0Var.b();
                    } catch (SecurityException unused) {
                        long jClearCallingIdentity = Binder.clearCallingIdentity();
                        try {
                            objB = y0Var.b();
                        } finally {
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                        }
                    }
                    return (String) objB;
                } catch (SecurityException e5) {
                    e = e5;
                    Log.e("GservicesLoader", "Unable to read GServices for: ".concat(String.valueOf(str)), e);
                    return null;
                }
            } catch (IllegalStateException e6) {
                e = e6;
                Log.e("GservicesLoader", "Unable to read GServices for: ".concat(String.valueOf(str)), e);
                return null;
            } catch (NullPointerException e10) {
                e = e10;
                Log.e("GservicesLoader", "Unable to read GServices for: ".concat(String.valueOf(str)), e);
                return null;
            }
        }
        return null;
    }

    public /* synthetic */ y0(y0 y0Var, String str) {
        this.f13146i = y0Var;
        this.f13147l = str;
    }

    public y0(Context context) {
        this.f13146i = context;
        p0 p0Var = new p0(1, null);
        this.f13147l = p0Var;
        context.getContentResolver().registerContentObserver(q0.f13093a, true, p0Var);
    }
}
