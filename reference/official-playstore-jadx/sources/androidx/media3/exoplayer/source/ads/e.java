package androidx.media3.exoplayer.source.ads;

import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.Timeline;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ads.AdsMediaSource;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class e implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3752i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3753l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3754m;

    public /* synthetic */ e(Object obj, Object obj2, int i10) {
        this.f3752i = i10;
        this.f3753l = obj;
        this.f3754m = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3752i) {
            case 0:
                ((AdsMediaSource.AdPrepareListener) this.f3753l).lambda$onPrepareComplete$0((MediaSource.MediaPeriodId) this.f3754m);
                break;
            case 1:
                ((AdsMediaSource.ComponentListener) this.f3753l).lambda$onAdPlaybackState$0((AdPlaybackState) this.f3754m);
                break;
            default:
                ((AdsMediaSource) this.f3753l).lambda$onChildSourceInfoRefreshed$2((Timeline) this.f3754m);
                break;
        }
    }
}
