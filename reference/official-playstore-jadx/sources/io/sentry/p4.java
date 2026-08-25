package io.sentry;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class p4 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f17514i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ y6 f17515l;

    public /* synthetic */ p4(y6 y6Var, int i10) {
        this.f17514i = i10;
        this.f17515l = y6Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f17514i) {
            case 0:
                this.f17515l.loadLazyFields();
                return;
            case 1:
                y6 y6Var = this.f17515l;
                String cacheDirPathWithoutDsn = y6Var.getCacheDirPathWithoutDsn();
                if (cacheDirPathWithoutDsn != null) {
                    File file = new File(cacheDirPathWithoutDsn, "app_start_profiling_config");
                    try {
                        qb.d.y(file);
                        if (y6Var.isEnableAppStartProfiling() || y6Var.isStartProfilerOnAppStart()) {
                            if (!y6Var.isStartProfilerOnAppStart() && !y6Var.isTracingEnabled()) {
                                y6Var.getLogger().q(w5.INFO, "Tracing is disabled and app start profiling will not start.", new Object[0]);
                                return;
                            }
                            if (file.createNewFile()) {
                                t4 t4Var = new t4(y6Var, y6Var.isEnableAppStartProfiling() ? y6Var.getInternalTracesSampler().a(new androidx.appcompat.app.i1(new u7("app.launch", io.sentry.protocol.h0.CUSTOM, "profile", null), Double.valueOf(io.sentry.util.q.a().c()))) : new androidx.core.provider.e(Boolean.FALSE, null));
                                FileOutputStream fileOutputStream = new FileOutputStream(file);
                                try {
                                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, s4.f17853e));
                                    try {
                                        y6Var.getSerializer().a(bufferedWriter, t4Var);
                                        bufferedWriter.close();
                                        fileOutputStream.close();
                                        return;
                                    } finally {
                                        try {
                                            break;
                                        } catch (Throwable th) {
                                        }
                                    }
                                } finally {
                                    try {
                                        break;
                                    } catch (Throwable th2) {
                                    }
                                }
                            }
                            return;
                        }
                        return;
                    } catch (Throwable th3) {
                        y6Var.getLogger().l(w5.ERROR, "Unable to create app start profiling config file. ", th3);
                        return;
                    }
                }
                return;
            case 2:
                y6 y6Var2 = this.f17515l;
                for (u0 u0Var : y6Var2.getOptionsObservers()) {
                    u0Var.g(y6Var2.getRelease());
                    u0Var.e(y6Var2.getProguardUuid());
                    u0Var.f(y6Var2.getSdkVersion());
                    u0Var.b(y6Var2.getDist());
                    u0Var.d(y6Var2.getEnvironment());
                    u0Var.a(y6Var2.getTags());
                    u0Var.c(y6Var2.getSessionReplay().f17218o);
                }
                io.sentry.cache.g gVarFindPersistingScopeObserver = y6Var2.findPersistingScopeObserver();
                if (gVarFindPersistingScopeObserver != null) {
                    try {
                        ((io.sentry.cache.tape.g) gVarFindPersistingScopeObserver.f17162b.a()).clear();
                        break;
                    } catch (IOException e5) {
                        gVarFindPersistingScopeObserver.f17161a.getLogger().l(w5.ERROR, "Failed to clear breadcrumbs from file queue", e5);
                    }
                    gVarFindPersistingScopeObserver.a("user.json");
                    gVarFindPersistingScopeObserver.a("level.json");
                    gVarFindPersistingScopeObserver.a("request.json");
                    gVarFindPersistingScopeObserver.a("fingerprint.json");
                    gVarFindPersistingScopeObserver.a("contexts.json");
                    gVarFindPersistingScopeObserver.a("extras.json");
                    gVarFindPersistingScopeObserver.a("tags.json");
                    gVarFindPersistingScopeObserver.a("trace.json");
                    gVarFindPersistingScopeObserver.a("transaction.json");
                    return;
                }
                return;
            default:
                s4.b().b(this.f17515l.getFlushTimeoutMillis());
                return;
        }
    }
}
