package io.sentry.util;

/* JADX INFO: loaded from: classes5.dex */
public final class k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j f17962b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile Object f17961a = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b f17963c = new b();

    public k(j jVar) {
        this.f17962b = jVar;
    }

    public final Object a() {
        if (this.f17961a == null) {
            a aVarA = this.f17963c.a();
            try {
                if (this.f17961a == null) {
                    this.f17961a = this.f17962b.g();
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
        return this.f17961a;
    }

    public final void b() {
        a aVarA = this.f17963c.a();
        try {
            this.f17961a = null;
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

    public final void c(Object obj) {
        a aVarA = this.f17963c.a();
        try {
            this.f17961a = obj;
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
