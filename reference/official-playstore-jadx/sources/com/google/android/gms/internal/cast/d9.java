package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class d9 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final d9 f13231c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final d9 f13232d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f13233a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Throwable f13234b;

    static {
        if (q9.f13557r) {
            f13232d = null;
            f13231c = null;
        } else {
            f13232d = new d9(null, false);
            f13231c = new d9(null, true);
        }
    }

    public d9(Throwable th, boolean z) {
        this.f13233a = z;
        this.f13234b = th;
    }
}
