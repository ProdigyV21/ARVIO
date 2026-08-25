package io.sentry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Date;

/* JADX INFO: loaded from: classes4.dex */
public final class o3 implements Runnable {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final Charset f17500l = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final y6 f17501i;

    public o3(y6 y6Var) {
        this.f17501i = y6Var;
    }

    public final Date a(File file) {
        y6 y6Var = this.f17501i;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), f17500l));
            try {
                String line = bufferedReader.readLine();
                y6Var.getLogger().q(w5.DEBUG, "Crash marker file has %s timestamp.", line);
                Date dateO = k2.c.o(line);
                bufferedReader.close();
                return dateO;
            } finally {
            }
        } catch (IOException e5) {
            y6Var.getLogger().l(w5.ERROR, "Error reading the crash marker file.", e5);
            return null;
        } catch (IllegalArgumentException e6) {
            y6Var.getLogger().k(w5.ERROR, e6, "Error converting the crash timestamp.", new Object[0]);
            return null;
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        y6 y6Var = this.f17501i;
        String cacheDirPath = y6Var.getCacheDirPath();
        if (cacheDirPath == null) {
            y6Var.getLogger().q(w5.INFO, "Cache dir is not set, not finalizing the previous session.", new Object[0]);
            return;
        }
        io.sentry.cache.d envelopeDiskCache = y6Var.getEnvelopeDiskCache();
        if ((envelopeDiskCache instanceof io.sentry.cache.c) && !((io.sentry.cache.c) envelopeDiskCache).g()) {
            y6Var.getLogger().q(w5.WARNING, "Timed out waiting to flush previous session to its own file in session finalizer.", new Object[0]);
            return;
        }
        Charset charset = io.sentry.cache.c.f17147s;
        File file = new File(cacheDirPath, "previous_session.json");
        i1 serializer = y6Var.getSerializer();
        if (file.exists()) {
            y6Var.getLogger().q(w5.WARNING, "Current session is not ended, we'd need to end it.", new Object[0]);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), f17500l));
                try {
                    j7 j7Var = (j7) serializer.e(bufferedReader, j7.class);
                    if (j7Var == null) {
                        y6Var.getLogger().q(w5.ERROR, "Stream from path %s resulted in a null envelope.", file.getAbsolutePath());
                    } else {
                        File file2 = new File(y6Var.getCacheDirPath(), ".sentry-native/last_crash");
                        i7 i7Var = j7Var.f17408q;
                        i7 i7Var2 = i7.Crashed;
                        if (i7Var == i7Var2) {
                            y4 y4Var = y4.f18066c;
                            io.sentry.util.a aVarA = y4Var.f18068b.a();
                            try {
                                y4Var.f18067a = false;
                                aVarA.close();
                                y4Var.a();
                            } finally {
                            }
                        } else if (file2.exists()) {
                            y6Var.getLogger().q(w5.INFO, "Crash marker file exists, last Session is gonna be Crashed.", new Object[0]);
                            Date dateA = a(file2);
                            j7Var.d(i7Var2, null, true, null);
                            j7Var.b(dateA);
                        } else if (j7Var.x == null) {
                            j7Var.b(k2.c.m());
                        }
                        if (file2.exists() && !file2.delete()) {
                            y6Var.getLogger().q(w5.ERROR, "Failed to delete the crash marker file. %s.", file2.getAbsolutePath());
                        }
                        s4.b().i(new fi.iki.elonen.f((io.sentry.protocol.v) null, y6Var.getSdkVersion(), h5.d(serializer, j7Var)), new j0());
                    }
                    bufferedReader.close();
                } finally {
                }
            } catch (Throwable th) {
                y6Var.getLogger().l(w5.ERROR, "Error processing previous session.", th);
            }
            if (file.delete()) {
                return;
            }
            y6Var.getLogger().q(w5.WARNING, "Failed to delete the previous session file.", new Object[0]);
        }
    }
}
