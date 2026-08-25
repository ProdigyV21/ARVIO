package androidx.media3.exoplayer.source.preload;

import androidx.media3.exoplayer.source.preload.DefaultPreloadManager;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class j implements com.google.common.base.r {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3799i;

    public /* synthetic */ j(int i10) {
        this.f3799i = i10;
    }

    @Override // com.google.common.base.r
    public final boolean apply(Object obj) {
        DefaultPreloadManager.PreloadStatus preloadStatus = (DefaultPreloadManager.PreloadStatus) obj;
        switch (this.f3799i) {
            case 0:
                return DefaultPreloadManager.PreloadMediaSourceControl.lambda$onSourcePrepared$0(preloadStatus);
            case 1:
                return DefaultPreloadManager.PreloadMediaSourceControl.lambda$onTracksSelected$1(preloadStatus);
            default:
                return DefaultPreloadManager.lambda$preloadMediaSourceHolderInternal$1(preloadStatus);
        }
    }
}
