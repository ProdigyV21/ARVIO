package io.sentry.android.replay;

import io.sentry.w5;
import io.sentry.y6;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.k0;
import x6.i0;

/* JADX INFO: loaded from: classes5.dex */
public final class m implements Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final y6 f16979i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final io.sentry.protocol.v f16980l;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public androidx.work.impl.u f16985q;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AtomicBoolean f16981m = new AtomicBoolean(false);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final io.sentry.util.b f16982n = new io.sentry.util.b();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final io.sentry.util.b f16983o = new io.sentry.util.b();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final io.sentry.util.b f16984p = new io.sentry.util.b();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final i0 f16986r = new i0(new k(this, 1));

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final ArrayList f16987s = new ArrayList();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final LinkedHashMap f16988t = new LinkedHashMap();

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final i0 f16989u = new i0(new k(this, 0));

    public m(y6 y6Var, io.sentry.protocol.v vVar) {
        this.f16979i = y6Var;
        this.f16980l = vVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IllegalAccessException, InvocationTargetException {
        io.sentry.util.a aVarA = this.f16982n.a();
        try {
            androidx.work.impl.u uVar = this.f16985q;
            if (uVar != null) {
                uVar.c();
            }
            this.f16985q = null;
            androidx.work.impl.t.h(aVarA, null);
            this.f16981m.set(true);
        } finally {
        }
    }

    public final void i(File file, String str, long j10) throws IllegalAccessException, InvocationTargetException {
        n nVar = new n(file, str, j10);
        io.sentry.util.a aVarA = this.f16984p.a();
        try {
            this.f16987s.add(nVar);
            androidx.work.impl.t.h(aVarA, null);
        } finally {
        }
    }

    public final void j(File file) {
        y6 y6Var = this.f16979i;
        try {
            if (file.delete()) {
                return;
            }
            y6Var.getLogger().q(w5.ERROR, "Failed to delete replay frame: %s", file.getAbsolutePath());
        } catch (Throwable th) {
            y6Var.getLogger().k(w5.ERROR, th, "Failed to delete replay frame: %s", file.getAbsolutePath());
        }
    }

    public final File k() {
        return (File) this.f16986r.getValue();
    }

    public final void l(String str, String str2) throws IllegalAccessException, InvocationTargetException {
        File file;
        File file2;
        i0 i0Var = this.f16989u;
        LinkedHashMap linkedHashMap = this.f16988t;
        io.sentry.util.a aVarA = this.f16983o.a();
        try {
            if (this.f16981m.get()) {
                androidx.work.impl.t.h(aVarA, null);
                return;
            }
            File file3 = (File) i0Var.getValue();
            if ((file3 == null || !file3.exists()) && (file = (File) i0Var.getValue()) != null) {
                file.createNewFile();
            }
            if (linkedHashMap.isEmpty() && (file2 = (File) i0Var.getValue()) != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file2), kotlin.text.a.f19924a), 8192);
                try {
                    Iterator it = new ga.a(new ga.p(bufferedReader, 5)).iterator();
                    while (it.hasNext()) {
                        List listY0 = kotlin.text.o.y0((String) it.next(), new String[]{"="}, 2, 2);
                        linkedHashMap.put((String) listY0.get(0), (String) listY0.get(1));
                    }
                    bufferedReader.close();
                } finally {
                }
            }
            if (str2 == null) {
                linkedHashMap.remove(str);
            } else {
                linkedHashMap.put(str, str2);
            }
            File file4 = (File) i0Var.getValue();
            if (file4 != null) {
                l7.i.v0(file4, kotlin.collections.x.u0(linkedHashMap.entrySet(), "\n", null, null, c.f16875m, 30));
            }
            androidx.work.impl.t.h(aVarA, null);
        } finally {
        }
    }

    public final String o(long j10) throws IllegalAccessException, InvocationTargetException {
        k0 k0Var = new k0();
        io.sentry.util.a aVarA = this.f16984p.a();
        try {
            kotlin.collections.x.M0(this.f16987s, new l(j10, this, k0Var, 0));
            androidx.work.impl.t.h(aVarA, null);
            return (String) k0Var.f19746i;
        } finally {
        }
    }
}
