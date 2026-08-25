package androidx.media3.exoplayer.drm;

import androidx.media3.common.Format;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManager;
import com.google.common.util.concurrent.q1;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class c implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3598i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3599l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3600m;

    public /* synthetic */ c(Object obj, Object obj2, int i10) {
        this.f3598i = i10;
        this.f3599l = obj;
        this.f3600m = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3598i) {
            case 0:
                ((DefaultDrmSessionManager.PreacquiredSessionReference) this.f3599l).lambda$acquire$0((Format) this.f3600m);
                break;
            default:
                ((OfflineLicenseHelper) this.f3599l).lambda$releaseManagerOnHandlerThread$4((q1) this.f3600m);
                break;
        }
    }
}
