package io.sentry;

import java.io.File;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes4.dex */
public final class n2 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final y6 f17483i;

    public n2(y6 y6Var) {
        this.f17483i = y6Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        y6 y6Var = this.f17483i;
        String cacheDirPath = y6Var.getCacheDirPath();
        if (cacheDirPath == null) {
            y6Var.getLogger().q(w5.INFO, "Cache dir is not set, not moving the previous session.", new Object[0]);
            return;
        }
        io.sentry.cache.d envelopeDiskCache = y6Var.getEnvelopeDiskCache();
        if (envelopeDiskCache instanceof io.sentry.cache.c) {
            Charset charset = io.sentry.cache.c.f17147s;
            io.sentry.cache.c cVar = (io.sentry.cache.c) envelopeDiskCache;
            cVar.c(new File(cacheDirPath, "session.json"), new File(cacheDirPath, "previous_session.json"));
            cVar.f17152o.countDown();
        }
    }
}
