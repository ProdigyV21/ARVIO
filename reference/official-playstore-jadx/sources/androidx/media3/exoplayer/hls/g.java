package androidx.media3.exoplayer.hls;

import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.hls.HlsInterstitialsAdsLoader;
import androidx.media3.exoplayer.source.ads.AdsMediaSource;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class g implements Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3646i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3647l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3648m;

    public /* synthetic */ g(Object obj, Object obj2, int i10) {
        this.f3646i = i10;
        this.f3647l = obj;
        this.f3648m = obj2;
    }

    @Override // androidx.media3.common.util.Consumer
    public final void accept(Object obj) {
        switch (this.f3646i) {
            case 0:
                ((HlsInterstitialsAdsLoader.LoaderCallback) this.f3647l).lambda$onLoadCompleted$1((HlsInterstitialsAdsLoader.AssetList) this.f3648m, (HlsInterstitialsAdsLoader.Listener) obj);
                break;
            case 1:
                HlsInterstitialsAdsLoader.PlayerListener.lambda$onPlaybackStateChanged$4((ExoPlayer) this.f3647l, this.f3648m, (HlsInterstitialsAdsLoader.Listener) obj);
                break;
            default:
                HlsInterstitialsAdsLoader.lambda$stop$4((AdsMediaSource) this.f3647l, (AdPlaybackState) this.f3648m, (HlsInterstitialsAdsLoader.Listener) obj);
                break;
        }
    }
}
