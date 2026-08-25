package io.sentry.cache;

import io.sentry.h5;
import io.sentry.i1;
import io.sentry.j0;
import io.sentry.j7;
import io.sentry.util.k;
import io.sentry.w5;
import io.sentry.y6;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public class c implements d {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final Charset f17147s = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final y6 f17148i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final k f17149l = new k(new a(this, 0));

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final File f17150m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f17151n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final CountDownLatch f17152o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final WeakHashMap f17153p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final io.sentry.util.b f17154q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final io.sentry.util.b f17155r;

    public c(y6 y6Var, String str, int i10) {
        ac.b.V(y6Var, "SentryOptions is required.");
        this.f17148i = y6Var;
        this.f17150m = new File(str);
        this.f17151n = i10;
        this.f17153p = new WeakHashMap();
        this.f17154q = new io.sentry.util.b();
        this.f17155r = new io.sentry.util.b();
        this.f17152o = new CountDownLatch(1);
    }

    @Override // io.sentry.cache.d
    public boolean R(fi.iki.elonen.f fVar, j0 j0Var) {
        return f(fVar, j0Var);
    }

    public final File[] a() {
        File file = this.f17150m;
        if (file.isDirectory() && file.canWrite() && file.canRead()) {
            File[] fileArrListFiles = file.listFiles(new d5.a(2));
            if (fileArrListFiles != null) {
                return fileArrListFiles;
            }
        } else {
            this.f17148i.getLogger().q(w5.ERROR, "The directory for caching files is inaccessible.: %s", file.getAbsolutePath());
        }
        return new File[0];
    }

    public final File b(fi.iki.elonen.f fVar) {
        String str;
        WeakHashMap weakHashMap = this.f17153p;
        io.sentry.util.a aVarA = this.f17154q.a();
        try {
            if (weakHashMap.containsKey(fVar)) {
                str = (String) weakHashMap.get(fVar);
            } else {
                String strConcat = qb.d.B().concat(".envelope");
                weakHashMap.put(fVar, strConcat);
                str = strConcat;
            }
            File file = new File(this.f17150m.getAbsolutePath(), str);
            aVarA.close();
            return file;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final void c(File file, File file2) {
        io.sentry.util.a aVarA = this.f17155r.a();
        try {
            if (!file.exists()) {
                aVarA.close();
                return;
            }
            boolean zExists = file2.exists();
            y6 y6Var = this.f17148i;
            if (zExists) {
                y6Var.getLogger().q(w5.DEBUG, "Previous session file already exists, deleting it.", new Object[0]);
                if (!file2.delete()) {
                    y6Var.getLogger().q(w5.WARNING, "Unable to delete previous session file: %s", file2);
                }
            }
            y6Var.getLogger().q(w5.INFO, "Moving current session to previous session.", new Object[0]);
            try {
                if (!file.renameTo(file2)) {
                    y6Var.getLogger().q(w5.WARNING, "Unable to move current session to previous session.", new Object[0]);
                }
            } catch (Throwable th) {
                y6Var.getLogger().l(w5.ERROR, "Error moving current session to previous session.", th);
            }
            aVarA.close();
        } catch (Throwable th2) {
            try {
                aVarA.close();
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
            }
            throw th2;
        }
    }

    public final fi.iki.elonen.f d(File file) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                fi.iki.elonen.f fVarC = ((i1) this.f17149l.a()).c(bufferedInputStream);
                bufferedInputStream.close();
                return fVarC;
            } finally {
            }
        } catch (IOException e5) {
            this.f17148i.getLogger().l(w5.ERROR, "Failed to deserialize the envelope.", e5);
            return null;
        }
    }

    public final j7 e(h5 h5Var) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(h5Var.f()), f17147s));
            try {
                j7 j7Var = (j7) ((i1) this.f17149l.a()).e(bufferedReader, j7.class);
                bufferedReader.close();
                return j7Var;
            } finally {
            }
        } catch (Throwable th) {
            this.f17148i.getLogger().l(w5.ERROR, "Failed to deserialize the session.", th);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00dc  */
    /* JADX WARN: Type inference failed for: r6v33 */
    /* JADX WARN: Type inference failed for: r6v49 */
    /* JADX WARN: Type inference failed for: r6v50 */
    /* JADX WARN: Type inference failed for: r7v56, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v57 */
    /* JADX WARN: Type inference failed for: r7v58 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean f(fi.iki.elonen.f r26, io.sentry.j0 r27) {
        /*
            Method dump skipped, instruction units count: 1407
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.cache.c.f(fi.iki.elonen.f, io.sentry.j0):boolean");
    }

    public final boolean g() {
        y6 y6Var = this.f17148i;
        try {
            return this.f17152o.await(y6Var.getSessionFlushTimeoutMillis(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            y6Var.getLogger().q(w5.DEBUG, "Timed out waiting for previous session to flush.", new Object[0]);
            return false;
        }
    }

    public final void h(File file, j7 j7Var) {
        String str = j7Var.f17406o;
        y6 y6Var = this.f17148i;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, f17147s));
                try {
                    y6Var.getLogger().q(w5.DEBUG, "Overwriting session to offline storage: %s", str);
                    ((i1) this.f17149l.a()).a(bufferedWriter, j7Var);
                    bufferedWriter.close();
                    fileOutputStream.close();
                } finally {
                }
            } finally {
            }
        } catch (Throwable th) {
            y6Var.getLogger().k(w5.ERROR, th, "Error writing Session to offline storage: %s", str);
        }
    }

    @Override // java.lang.Iterable
    public final Iterator<fi.iki.elonen.f> iterator() {
        y6 y6Var = this.f17148i;
        File[] fileArrA = a();
        ArrayList arrayList = new ArrayList(fileArrA.length);
        for (File file : fileArrA) {
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                try {
                    arrayList.add(((i1) this.f17149l.a()).c(bufferedInputStream));
                    bufferedInputStream.close();
                } catch (Throwable th) {
                    try {
                        bufferedInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (FileNotFoundException unused) {
                y6Var.getLogger().q(w5.DEBUG, "Envelope file '%s' disappeared while converting all cached files to envelopes.", file.getAbsolutePath());
            } catch (IOException e5) {
                y6Var.getLogger().l(w5.ERROR, "Error while reading cached envelope from file " + file.getAbsolutePath(), e5);
            }
        }
        return arrayList.iterator();
    }

    @Override // io.sentry.cache.d
    public final void q0(fi.iki.elonen.f fVar) {
        ac.b.V(fVar, "Envelope is required.");
        File fileB = b(fVar);
        boolean zDelete = fileB.delete();
        y6 y6Var = this.f17148i;
        if (zDelete) {
            y6Var.getLogger().q(w5.DEBUG, "Discarding envelope from cache: %s", fileB.getAbsolutePath());
        } else {
            y6Var.getLogger().q(w5.DEBUG, "Envelope was not cached or could not be deleted: %s", fileB.getAbsolutePath());
        }
    }
}
