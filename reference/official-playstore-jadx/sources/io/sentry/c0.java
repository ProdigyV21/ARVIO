package io.sentry;

import java.io.File;

/* JADX INFO: loaded from: classes4.dex */
public final class c0 extends x implements s0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c1 f17113e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final i1 f17114f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ILogger f17115g;

    public c0(c1 c1Var, i1 i1Var, ILogger iLogger, long j10, int i10) {
        super(c1Var, iLogger, j10, i10);
        ac.b.V(c1Var, "Scopes are required.");
        this.f17113e = c1Var;
        ac.b.V(i1Var, "Serializer is required.");
        this.f17114f = i1Var;
        ac.b.V(iLogger, "Logger is required.");
        this.f17115g = iLogger;
    }

    public static void c(c0 c0Var, File file, io.sentry.hints.l lVar) {
        ILogger iLogger = c0Var.f17115g;
        if (lVar.a()) {
            iLogger.q(w5.INFO, "File not deleted since retry was marked. %s.", file.getAbsolutePath());
            return;
        }
        try {
            if (!file.delete()) {
                iLogger.q(w5.ERROR, "Failed to delete '%s' %s", file.getAbsolutePath(), "after trying to capture it");
            }
        } catch (Throwable th) {
            iLogger.k(w5.ERROR, th, "Failed to delete '%s' %s", file.getAbsolutePath(), "after trying to capture it");
        }
        iLogger.q(w5.DEBUG, "Deleted file %s.", file.getAbsolutePath());
    }

    @Override // io.sentry.x
    public final boolean a(String str) {
        return str.endsWith(".envelope");
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0116, code lost:
    
        if (r2 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0118, code lost:
    
        c(r10, r11, (io.sentry.hints.l) r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0141, code lost:
    
        if (r2 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0161, code lost:
    
        if (r2 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0164, code lost:
    
        return;
     */
    @Override // io.sentry.x
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(java.io.File r11, io.sentry.j0 r12) {
        /*
            Method dump skipped, instruction units count: 383
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.c0.b(java.io.File, io.sentry.j0):void");
    }
}
