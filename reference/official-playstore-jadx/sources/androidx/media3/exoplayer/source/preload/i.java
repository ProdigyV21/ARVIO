package androidx.media3.exoplayer.source.preload;

import androidx.media3.exoplayer.source.preload.DefaultPreloadManager;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class i implements com.google.common.base.r {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3797i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ DefaultPreloadManager.PreloadStatus f3798l;

    public /* synthetic */ i(DefaultPreloadManager.PreloadStatus preloadStatus, int i10) {
        this.f3797i = i10;
        this.f3798l = preloadStatus;
    }

    @Override // com.google.common.base.r
    public final boolean apply(Object obj) {
        switch (this.f3797i) {
            case 0:
                return DefaultPreloadManager.PreCacheHelperListener.lambda$onPrepareError$1(this.f3798l, (DefaultPreloadManager.PreloadStatus) obj);
            case 1:
                return DefaultPreloadManager.PreCacheHelperListener.lambda$onPreCacheProgress$0(this.f3798l, (DefaultPreloadManager.PreloadStatus) obj);
            case 2:
                return DefaultPreloadManager.PreCacheHelperListener.lambda$onDownloadError$2(this.f3798l, (DefaultPreloadManager.PreloadStatus) obj);
            case 3:
                return DefaultPreloadManager.PreloadMediaSourceControl.lambda$onUsedByPlayer$3(this.f3798l, (DefaultPreloadManager.PreloadStatus) obj);
            case 4:
                return DefaultPreloadManager.PreloadMediaSourceControl.lambda$onPreloadError$5(this.f3798l, (DefaultPreloadManager.PreloadStatus) obj);
            case 5:
                return DefaultPreloadManager.PreloadMediaSourceControl.lambda$onLoadedToTheEndOfSource$4(this.f3798l, (DefaultPreloadManager.PreloadStatus) obj);
            default:
                return DefaultPreloadManager.PreloadMediaSourceControl.lambda$continueOrCompletePreloading$6(this.f3798l, (DefaultPreloadManager.PreloadStatus) obj);
        }
    }
}
