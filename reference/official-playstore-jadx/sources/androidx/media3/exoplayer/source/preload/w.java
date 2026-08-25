package androidx.media3.exoplayer.source.preload;

import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.preload.PreloadMediaSource;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class w implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3824i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ PreloadMediaSource.PreloadMediaPeriodCallback f3825l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MediaPeriod f3826m;

    public /* synthetic */ w(PreloadMediaSource.PreloadMediaPeriodCallback preloadMediaPeriodCallback, MediaPeriod mediaPeriod, int i10) {
        this.f3824i = i10;
        this.f3825l = preloadMediaPeriodCallback;
        this.f3826m = mediaPeriod;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3824i) {
            case 0:
                this.f3825l.lambda$onPrepared$0(this.f3826m);
                break;
            default:
                this.f3825l.lambda$onContinueLoadingRequested$1(this.f3826m);
                break;
        }
    }
}
