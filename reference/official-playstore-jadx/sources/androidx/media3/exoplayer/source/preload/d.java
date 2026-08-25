package androidx.media3.exoplayer.source.preload;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.source.preload.BasePreloadManager;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d implements ListenerSet.Event {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3789i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ BasePreloadManager.MediaSourceHolder f3790l;

    public /* synthetic */ d(BasePreloadManager.MediaSourceHolder mediaSourceHolder, int i10) {
        this.f3789i = i10;
        this.f3790l = mediaSourceHolder;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object obj) {
        switch (this.f3789i) {
            case 0:
                BasePreloadManager.lambda$onCompleted$0(this.f3790l, (PreloadManagerListener) obj);
                break;
            default:
                BasePreloadManager.lambda$onCompleted$2(this.f3790l, (PreloadManagerListener) obj);
                break;
        }
    }
}
