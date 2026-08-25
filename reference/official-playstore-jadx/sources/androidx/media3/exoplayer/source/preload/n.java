package androidx.media3.exoplayer.source.preload;

import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.source.preload.PreCacheHelper;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class n implements Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3807i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ PreCacheHelper.DownloadCallback f3808l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3809m;

    public /* synthetic */ n(PreCacheHelper.DownloadCallback downloadCallback, Object obj, int i10) {
        this.f3807i = i10;
        this.f3808l = downloadCallback;
        this.f3809m = obj;
    }

    @Override // androidx.media3.common.util.Consumer
    public final void accept(Object obj) {
        switch (this.f3807i) {
            case 0:
                this.f3808l.lambda$onPrepared$0((MediaItem) this.f3809m, (PreCacheHelper.Listener) obj);
                break;
            default:
                this.f3808l.lambda$onDownloadProgress$4((PreCacheHelper.Task) this.f3809m, (PreCacheHelper.Listener) obj);
                break;
        }
    }
}
