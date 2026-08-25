package io.sentry.android.core;

/* JADX INFO: loaded from: classes5.dex */
public final class t1 implements Comparable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f16749i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final long f16750l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f16751m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f16752n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final boolean f16753o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f16754p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final long f16755q;

    public t1(long j10) {
        this(j10, j10, 0L, 0L, false, false, 0L);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Long.compare(this.f16750l, ((t1) obj).f16750l);
    }

    public t1(long j10, long j11, long j12, long j13, boolean z, boolean z5, long j14) {
        this.f16749i = j10;
        this.f16750l = j11;
        this.f16751m = j12;
        this.f16752n = j13;
        this.f16753o = z;
        this.f16754p = z5;
        this.f16755q = j14;
    }
}
