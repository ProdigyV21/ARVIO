package com.google.common.base;

/* JADX INFO: loaded from: classes4.dex */
public final class e0 implements c0 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final androidx.media3.exoplayer.analytics.z f13904n = new androidx.media3.exoplayer.analytics.z(1);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f13905i = new Object();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile c0 f13906l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f13907m;

    public e0(c0 c0Var) {
        this.f13906l = c0Var;
    }

    @Override // com.google.common.base.c0
    public final Object get() {
        c0 c0Var = this.f13906l;
        androidx.media3.exoplayer.analytics.z zVar = f13904n;
        if (c0Var != zVar) {
            synchronized (this.f13905i) {
                try {
                    if (this.f13906l != zVar) {
                        Object obj = this.f13906l.get();
                        this.f13907m = obj;
                        this.f13906l = zVar;
                        return obj;
                    }
                } finally {
                }
            }
        }
        return this.f13907m;
    }

    public final String toString() {
        Object obj = this.f13906l;
        StringBuilder sb2 = new StringBuilder("Suppliers.memoize(");
        if (obj == f13904n) {
            obj = "<supplier that returned " + this.f13907m + ">";
        }
        sb2.append(obj);
        sb2.append(")");
        return sb2.toString();
    }
}
