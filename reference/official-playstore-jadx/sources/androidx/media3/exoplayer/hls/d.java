package androidx.media3.exoplayer.hls;

import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.PlayerMessage;
import androidx.media3.exoplayer.hls.HlsInterstitialsAdsLoader;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d implements Consumer, PlayerMessage.Target {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3641i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3642l;

    public /* synthetic */ d(Object obj, int i10) {
        this.f3641i = i10;
        this.f3642l = obj;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        switch (this.f3641i) {
            case 0:
                HlsInterstitialsAdsLoader.lambda$startLoadingAssetList$5((HlsInterstitialsAdsLoader.AssetListData) this.f3642l, (HlsInterstitialsAdsLoader.Listener) obj);
                break;
            default:
                ((HlsInterstitialsAdsLoader.LoaderCallback) this.f3642l).lambda$onLoadCompleted$0((HlsInterstitialsAdsLoader.Listener) obj);
                break;
        }
    }

    @Override // androidx.media3.exoplayer.PlayerMessage.Target
    public void handleMessage(int i10, Object obj) {
        ((HlsInterstitialsAdsLoader.RunnableAtPosition) this.f3642l).run();
    }
}
