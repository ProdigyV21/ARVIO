package androidx.media3.exoplayer.source.ads;

import androidx.media3.exoplayer.source.ads.AdsMediaSource;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class c implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3745i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ AdsMediaSource f3746l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ AdsMediaSource.ComponentListener f3747m;

    public /* synthetic */ c(AdsMediaSource adsMediaSource, AdsMediaSource.ComponentListener componentListener, int i10) {
        this.f3745i = i10;
        this.f3746l = adsMediaSource;
        this.f3747m = componentListener;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3745i) {
            case 0:
                this.f3746l.lambda$prepareSourceInternal$0(this.f3747m);
                break;
            default:
                this.f3746l.lambda$releaseSourceInternal$1(this.f3747m);
                break;
        }
    }
}
