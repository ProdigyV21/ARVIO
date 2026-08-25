package y8;

import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* JADX INFO: loaded from: classes5.dex */
public final class e0 {
    public static final e0 k = new e0(false, false, false, false, false, new e0(false, false, false, false, false, null, false, null, null, AnalyticsListener.EVENT_DRM_KEYS_LOADED), false, null, null, 988);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f22923a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f22924b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f22925c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f22926d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f22927e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final e0 f22928f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f22929g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final e0 f22930h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final e0 f22931i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f22932j;

    public e0(boolean z, boolean z5, boolean z10, boolean z11, boolean z12, e0 e0Var, boolean z13, e0 e0Var2, e0 e0Var3, int i10) {
        z = (i10 & 1) != 0 ? true : z;
        z5 = (i10 & 2) != 0 ? true : z5;
        z10 = (i10 & 4) != 0 ? false : z10;
        z11 = (i10 & 8) != 0 ? false : z11;
        z12 = (i10 & 16) != 0 ? false : z12;
        e0Var = (i10 & 32) != 0 ? null : e0Var;
        z13 = (i10 & 64) != 0 ? true : z13;
        e0Var2 = (i10 & 128) != 0 ? e0Var : e0Var2;
        e0Var3 = (i10 & 256) != 0 ? e0Var : e0Var3;
        boolean z14 = (i10 & 512) == 0;
        this.f22923a = z;
        this.f22924b = z5;
        this.f22925c = z10;
        this.f22926d = z11;
        this.f22927e = z12;
        this.f22928f = e0Var;
        this.f22929g = z13;
        this.f22930h = e0Var2;
        this.f22931i = e0Var3;
        this.f22932j = z14;
    }
}
