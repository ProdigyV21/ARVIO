package io.sentry.android.core.performance;

import android.os.SystemClock;

/* JADX INFO: loaded from: classes5.dex */
public final class h implements Comparable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f16720i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f16721l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f16722m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f16723n;

    public final long a() {
        long j10 = this.f16723n;
        if (j10 != 0) {
            return j10 - this.f16722m;
        }
        return 0L;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Long.compare(this.f16721l, ((h) obj).f16721l);
    }

    public final boolean d() {
        return this.f16722m != 0;
    }

    public final void f(long j10) {
        this.f16722m = j10;
        this.f16721l = System.currentTimeMillis() - (SystemClock.uptimeMillis() - this.f16722m);
    }
}
