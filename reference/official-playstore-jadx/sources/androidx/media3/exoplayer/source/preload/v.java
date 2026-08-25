package androidx.media3.exoplayer.source.preload;

import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.preload.PreloadMediaSource;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class v implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3820i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3821l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3822m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f3823n;

    public /* synthetic */ v(Object obj, Object obj2, Object obj3, int i10) {
        this.f3820i = i10;
        this.f3821l = obj;
        this.f3822m = obj2;
        this.f3823n = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3820i) {
            case 0:
                ((PreloadMediaSource.PreloadMediaPeriodCallback) this.f3821l).lambda$maybeContinueLoading$2((MediaPeriod) this.f3822m, (MediaSource.MediaPeriodId) this.f3823n);
                break;
            case 1:
                ((BasePreloadManager) this.f3821l).lambda$onMediaSourceUpdated$9((MediaItem) this.f3822m, (MediaSource) this.f3823n);
                break;
            default:
                ((BasePreloadManager) this.f3821l).lambda$onCompleted$3((MediaItem) this.f3822m, (com.google.common.base.r) this.f3823n);
                break;
        }
    }
}
