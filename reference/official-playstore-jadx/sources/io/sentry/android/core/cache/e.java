package io.sentry.android.core.cache;

import android.os.SystemClock;
import fi.iki.elonen.f;
import io.sentry.ILogger;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.android.core.b0;
import io.sentry.android.core.performance.g;
import io.sentry.android.core.performance.h;
import io.sentry.android.core.x1;
import io.sentry.j0;
import io.sentry.w5;
import io.sentry.y6;
import io.sentry.y7;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class e extends io.sentry.cache.c {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final List f16465u;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final io.sentry.android.core.internal.util.d f16466t;

    static {
        final int i10 = 0;
        final int i11 = 1;
        f16465u = Arrays.asList(new d(b0.class, "ANR", "last_anr_report", new c() { // from class: io.sentry.android.core.cache.a
            @Override // io.sentry.android.core.cache.c
            public final Long a(Object obj) {
                switch (i10) {
                    case 0:
                        return Long.valueOf(((b0) obj).f16448n);
                    default:
                        return Long.valueOf(((x1) obj).f16825n);
                }
            }
        }), new d(x1.class, "Tombstone", "last_tombstone_report", new c() { // from class: io.sentry.android.core.cache.a
            @Override // io.sentry.android.core.cache.c
            public final Long a(Object obj) {
                switch (i11) {
                    case 0:
                        return Long.valueOf(((b0) obj).f16448n);
                    default:
                        return Long.valueOf(((x1) obj).f16825n);
                }
            }
        }));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public e(SentryAndroidOptions sentryAndroidOptions) {
        String cacheDirPath = sentryAndroidOptions.getCacheDirPath();
        ac.b.V(cacheDirPath, "cacheDirPath must not be null");
        super(sentryAndroidOptions, cacheDirPath, sentryAndroidOptions.getMaxCacheItems());
        this.f16466t = io.sentry.android.core.internal.util.d.f16599i;
    }

    public static Long k(y6 y6Var, String str, String str2) {
        String cacheDirPath = y6Var.getCacheDirPath();
        ac.b.V(cacheDirPath, "Cache dir path should be set for getting " + str2 + "s reported");
        File file = new File(cacheDirPath, str);
        try {
            String strM = qb.d.M(file);
            if (strM != null && !strM.equals("null")) {
                return Long.valueOf(Long.parseLong(strM.trim()));
            }
            return null;
        } catch (Throwable th) {
            if (th instanceof FileNotFoundException) {
                y6Var.getLogger().q(w5.DEBUG, a0.c.l("Last ", str2, " marker does not exist. %s."), file.getAbsolutePath());
                return null;
            }
            y6Var.getLogger().l(w5.ERROR, a0.c.l("Error reading last ", str2, " marker"), th);
            return null;
        }
    }

    @Override // io.sentry.cache.c, io.sentry.cache.d
    public final boolean R(f fVar, j0 j0Var) {
        boolean zF = f(fVar, j0Var);
        y6 y6Var = this.f17148i;
        SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) y6Var;
        h hVar = g.c().f16710o;
        if (y7.class.isInstance(j0Var.b("sentry:typeCheckHint")) && hVar.d()) {
            this.f16466t.getClass();
            long jUptimeMillis = SystemClock.uptimeMillis() - hVar.f16722m;
            if (jUptimeMillis <= sentryAndroidOptions.getStartupCrashDurationThresholdMillis()) {
                ILogger logger = sentryAndroidOptions.getLogger();
                w5 w5Var = w5.DEBUG;
                logger.q(w5Var, "Startup Crash detected %d milliseconds after SDK init. Writing a startup crash marker file to disk.", Long.valueOf(jUptimeMillis));
                String outboxPath = y6Var.getOutboxPath();
                if (outboxPath == null) {
                    y6Var.getLogger().q(w5Var, "Outbox path is null, the startup crash marker file will not be written", new Object[0]);
                } else {
                    try {
                        new File(outboxPath, "startup_crash").createNewFile();
                    } catch (Throwable th) {
                        y6Var.getLogger().l(w5.ERROR, "Error writing the startup crash marker file to the disk", th);
                    }
                }
            }
        }
        for (d dVar : f16465u) {
            Class cls = dVar.f16461a;
            b bVar = new b(dVar, sentryAndroidOptions, this);
            Object objB = j0Var.b("sentry:typeCheckHint");
            if (cls.isInstance(j0Var.b("sentry:typeCheckHint")) && objB != null) {
                d dVar2 = bVar.f16458a;
                Long lA = dVar2.f16464d.a(objB);
                ILogger logger2 = bVar.f16459b.getLogger();
                w5 w5Var2 = w5.DEBUG;
                String str = dVar2.f16462b;
                logger2.q(w5Var2, "Writing last reported %s marker with timestamp %d", str, lA);
                String str2 = dVar2.f16463c;
                y6 y6Var2 = bVar.f16460c.f17148i;
                String cacheDirPath = y6Var2.getCacheDirPath();
                if (cacheDirPath == null) {
                    y6Var2.getLogger().q(w5Var2, a0.c.l("Cache dir path is null, the ", str, " marker will not be written"), new Object[0]);
                } else {
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(new File(cacheDirPath, str2));
                        try {
                            fileOutputStream.write(String.valueOf(lA).getBytes(io.sentry.cache.c.f17147s));
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        } catch (Throwable th2) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable th3) {
                                th2.addSuppressed(th3);
                            }
                            throw th2;
                        }
                    } catch (Throwable th4) {
                        y6Var2.getLogger().l(w5.ERROR, a0.c.l("Error writing the ", str, " marker to the disk"), th4);
                    }
                }
            }
        }
        return zF;
    }
}
