package androidx.media3.exoplayer.hls;

import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.hls.HlsInterstitialsAdsLoader;
import androidx.media3.exoplayer.source.ads.AdsMediaSource;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class i implements Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3652i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3653l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3654m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f3655n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f3656o;

    public /* synthetic */ i(Object obj, Object obj2, int i10, int i11, int i12) {
        this.f3652i = i12;
        this.f3656o = obj;
        this.f3653l = obj2;
        this.f3654m = i10;
        this.f3655n = i11;
    }

    @Override // androidx.media3.common.util.Consumer
    public final void accept(Object obj) {
        switch (this.f3652i) {
            case 0:
                ((HlsInterstitialsAdsLoader.Listener) obj).onAdCompleted((MediaItem) this.f3656o, this.f3653l, this.f3654m, this.f3655n);
                break;
            default:
                HlsInterstitialsAdsLoader.lambda$handlePrepareComplete$2((AdsMediaSource) this.f3656o, this.f3653l, this.f3654m, this.f3655n, (HlsInterstitialsAdsLoader.Listener) obj);
                break;
        }
    }
}
