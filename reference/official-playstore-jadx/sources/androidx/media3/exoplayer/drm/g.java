package androidx.media3.exoplayer.drm;

import androidx.media3.exoplayer.drm.DrmSessionEventListener;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class g implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3603i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ DrmSessionEventListener.EventDispatcher f3604l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ DrmSessionEventListener f3605m;

    public /* synthetic */ g(DrmSessionEventListener.EventDispatcher eventDispatcher, DrmSessionEventListener drmSessionEventListener, int i10) {
        this.f3603i = i10;
        this.f3604l = eventDispatcher;
        this.f3605m = drmSessionEventListener;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3603i) {
            case 0:
                this.f3604l.lambda$drmKeysRemoved$4(this.f3605m);
                break;
            case 1:
                this.f3604l.lambda$drmKeysRestored$3(this.f3605m);
                break;
            default:
                this.f3604l.lambda$drmSessionReleased$5(this.f3605m);
                break;
        }
    }
}
