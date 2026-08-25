package androidx.media3.exoplayer.drm;

import androidx.media3.exoplayer.drm.DefaultDrmSessionManager;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3601i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3602l;

    public /* synthetic */ d(Object obj, int i10) {
        this.f3601i = i10;
        this.f3602l = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3601i) {
            case 0:
                ((DefaultDrmSessionManager.PreacquiredSessionReference) this.f3602l).lambda$release$1();
                break;
            default:
                ((DefaultDrmSession) this.f3602l).release(null);
                break;
        }
    }
}
