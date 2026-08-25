package io.sentry.android.core.anr;

/* JADX INFO: loaded from: classes5.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f16419a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f16420b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final StackTraceElement[] f16421c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f16422d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f16423e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f16424f = 1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f16425g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f16426h;

    public a(StackTraceElement[] stackTraceElementArr, int i10, int i11, long j10, float f10) {
        this.f16421c = stackTraceElementArr;
        this.f16422d = i10;
        this.f16423e = i11;
        this.f16419a = (i11 - i10) + 1;
        this.f16425g = j10;
        this.f16426h = j10;
        this.f16420b = f10;
    }
}
