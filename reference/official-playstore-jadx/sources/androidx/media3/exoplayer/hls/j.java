package androidx.media3.exoplayer.hls;

import androidx.media3.common.MediaItem;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.hls.HlsInterstitialsAdsLoader;
import androidx.media3.exoplayer.source.ads.AdsMediaSource;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class j implements Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3657i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3658l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3659m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f3660n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f3661o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f3662p;

    public /* synthetic */ j(int i10, int i11, int i12, Object obj, Object obj2, Object obj3) {
        this.f3657i = i12;
        this.f3661o = obj;
        this.f3658l = obj2;
        this.f3659m = i10;
        this.f3660n = i11;
        this.f3662p = obj3;
    }

    @Override // androidx.media3.common.util.Consumer
    public final void accept(Object obj) {
        switch (this.f3657i) {
            case 0:
                ((HlsInterstitialsAdsLoader.Listener) obj).onMetadata((MediaItem) this.f3661o, this.f3658l, this.f3659m, this.f3660n, (Metadata) this.f3662p);
                break;
            default:
                HlsInterstitialsAdsLoader.lambda$handlePrepareError$3((AdsMediaSource) this.f3661o, this.f3658l, this.f3659m, this.f3660n, (IOException) this.f3662p, (HlsInterstitialsAdsLoader.Listener) obj);
                break;
        }
    }
}
