package com.google.android.gms.internal.auth;

import android.content.ContentResolver;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Binder;
import android.os.StrictMode;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class t0 implements x0 {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final t.e f13114q = new t.e(0);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final String[] f13115r = {"key", "value"};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ContentResolver f13116i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Uri f13117l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final androidx.cursoradapter.widget.a f13118m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f13119n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public volatile Map f13120o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ArrayList f13121p;

    public t0(ContentResolver contentResolver, Uri uri) {
        androidx.cursoradapter.widget.a aVar = new androidx.cursoradapter.widget.a(this);
        this.f13118m = aVar;
        this.f13119n = new Object();
        this.f13121p = new ArrayList();
        contentResolver.getClass();
        uri.getClass();
        this.f13116i = contentResolver;
        this.f13117l = uri;
        contentResolver.registerContentObserver(uri, false, aVar);
    }

    public static t0 a(ContentResolver contentResolver, Uri uri) {
        t0 t0Var;
        synchronized (t0.class) {
            t.e eVar = f13114q;
            t0Var = (t0) eVar.get(uri);
            if (t0Var == null) {
                try {
                    t0 t0Var2 = new t0(contentResolver, uri);
                    try {
                        eVar.put(uri, t0Var2);
                    } catch (SecurityException unused) {
                    }
                    t0Var = t0Var2;
                } catch (SecurityException unused2) {
                }
            }
        }
        return t0Var;
    }

    public static synchronized void c() {
        try {
            for (t0 t0Var : (t.d) f13114q.values()) {
                t0Var.f13116i.unregisterContentObserver(t0Var.f13118m);
            }
            f13114q.clear();
        } catch (Throwable th) {
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r0v4, types: [android.os.StrictMode$ThreadPolicy, java.util.Map] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    public final Object b(String str) {
        Map map;
        ?? r02;
        Object objZza;
        Map map2 = this.f13120o;
        ?? r03 = map2;
        if (map2 == null) {
            synchronized (this.f13119n) {
                ?? r04 = this.f13120o;
                if (r04 != 0) {
                    r02 = r04;
                } else {
                    try {
                        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                        try {
                            p2 p2Var = new p2(this, 2);
                            try {
                                objZza = p2Var.zza();
                            } catch (SecurityException unused) {
                                long jClearCallingIdentity = Binder.clearCallingIdentity();
                                try {
                                    objZza = p2Var.zza();
                                } finally {
                                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                                }
                            }
                            map = (Map) objZza;
                            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                        } catch (SQLiteException | IllegalStateException | SecurityException unused2) {
                            Log.e("ConfigurationContentLdr", "PhenotypeFlag unable to load ContentProvider, using default values");
                            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                            map = null;
                        }
                        this.f13120o = map;
                        r02 = map;
                    } catch (Throwable th) {
                        StrictMode.setThreadPolicy(r04);
                        throw th;
                    }
                }
            }
            r03 = r02;
        }
        if (r03 == 0) {
            r03 = Collections.EMPTY_MAP;
        }
        return (String) r03.get(str);
    }
}
