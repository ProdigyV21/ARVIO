package io.sentry.cache.tape;

/* JADX INFO: loaded from: classes5.dex */
public final class h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final h f17169c = new h(0, 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f17170a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f17171b;

    public h(long j10, int i10) {
        this.f17170a = j10;
        this.f17171b = i10;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(h.class.getSimpleName());
        sb2.append("[position=");
        sb2.append(this.f17170a);
        sb2.append(", length=");
        return androidx.compose.material3.d.j(this.f17171b, "]", sb2);
    }
}
