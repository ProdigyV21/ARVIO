package androidx.media3.exoplayer.drm;

import com.google.common.util.concurrent.q1;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class n implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3612i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ OfflineLicenseHelper f3613l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ DrmSession f3614m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ q1 f3615n;

    public /* synthetic */ n(DrmSession drmSession, OfflineLicenseHelper offlineLicenseHelper, q1 q1Var) {
        this.f3612i = 1;
        this.f3613l = offlineLicenseHelper;
        this.f3614m = drmSession;
        this.f3615n = q1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3612i) {
            case 0:
                this.f3613l.lambda$getLicenseDurationRemainingSec$0(this.f3615n, this.f3614m);
                break;
            case 1:
                this.f3613l.lambda$acquireFirstSessionOnHandlerThread$3(this.f3614m, this.f3615n);
                break;
            default:
                this.f3613l.lambda$acquireSessionAndGetOfflineLicenseKeySetIdOnHandlerThread$1(this.f3615n, this.f3614m);
                break;
        }
    }

    public /* synthetic */ n(OfflineLicenseHelper offlineLicenseHelper, q1 q1Var, DrmSession drmSession, int i10) {
        this.f3612i = i10;
        this.f3613l = offlineLicenseHelper;
        this.f3615n = q1Var;
        this.f3614m = drmSession;
    }
}
