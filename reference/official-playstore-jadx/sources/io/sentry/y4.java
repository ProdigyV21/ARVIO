package io.sentry;

/* JADX INFO: loaded from: classes4.dex */
public final class y4 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final y4 f18066c = new y4();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f18067a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final io.sentry.util.b f18068b = new io.sentry.util.b();

    public final void a() {
        io.sentry.util.a aVarA = this.f18068b.a();
        try {
            if (!this.f18067a) {
                this.f18067a = true;
            }
            aVarA.close();
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
