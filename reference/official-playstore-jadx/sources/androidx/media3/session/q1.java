package androidx.media3.session;

import androidx.media3.session.legacy.MediaBrowserServiceCompat;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class q1 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4117i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ com.google.common.util.concurrent.d1 f4118l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MediaBrowserServiceCompat.Result f4119m;

    public /* synthetic */ q1(com.google.common.util.concurrent.d1 d1Var, MediaBrowserServiceCompat.Result result, int i10) {
        this.f4117i = i10;
        this.f4118l = d1Var;
        this.f4119m = result;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4117i) {
            case 0:
                MediaLibraryServiceLegacyStub.lambda$sendLibraryResultWithMediaItemWhenReady$8(this.f4118l, this.f4119m);
                break;
            case 1:
                MediaLibraryServiceLegacyStub.lambda$sendCustomActionResultWhenReady$7(this.f4118l, this.f4119m);
                break;
            default:
                MediaLibraryServiceLegacyStub.lambda$sendLibraryResultWithMediaItemsWhenReady$9(this.f4118l, this.f4119m);
                break;
        }
    }
}
