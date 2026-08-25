package io.sentry;

/* JADX INFO: loaded from: classes4.dex */
public final class o4 implements n4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f17502a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final io.sentry.android.core.q f17503b;

    public /* synthetic */ o4(io.sentry.android.core.q qVar, int i10) {
        this.f17502a = i10;
        this.f17503b = qVar;
    }

    @Override // io.sentry.n4
    public final androidx.credentials.playservices.b a(c1 c1Var, y6 y6Var) {
        switch (this.f17502a) {
            case 0:
                ac.b.V(c1Var, "Scopes are required");
                ac.b.V(y6Var, "SentryOptions is required");
                String cacheDirPath = this.f17503b.f16726l.getCacheDirPath();
                if (cacheDirPath == null || !g8.b.a(cacheDirPath, y6Var.getLogger())) {
                    y6Var.getLogger().q(w5.ERROR, "No cache dir path is defined in options.", new Object[0]);
                }
                break;
            default:
                ac.b.V(c1Var, "Scopes are required");
                ac.b.V(y6Var, "SentryOptions is required");
                String outboxPath = this.f17503b.f16726l.getOutboxPath();
                if (outboxPath == null || !g8.b.a(outboxPath, y6Var.getLogger())) {
                    y6Var.getLogger().q(w5.ERROR, "No outbox dir path is defined in options.", new Object[0]);
                }
                break;
        }
        return null;
    }

    @Override // io.sentry.n4
    public final /* synthetic */ boolean b(String str, ILogger iLogger) {
        int i10 = this.f17502a;
        return g8.b.a(str, iLogger);
    }
}
