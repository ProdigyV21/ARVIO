package androidx.media3.exoplayer.source.preload;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class t implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3818i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ PreloadMediaSource f3819l;

    public /* synthetic */ t(PreloadMediaSource preloadMediaSource, int i10) {
        this.f3818i = i10;
        this.f3819l = preloadMediaSource;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3818i) {
            case 0:
                this.f3819l.lambda$releasePreloadMediaSource$3();
                break;
            case 1:
                this.f3819l.lambda$clear$1();
                break;
            default:
                this.f3819l.checkForPreloadError();
                break;
        }
    }
}
