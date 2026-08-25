package androidx.media3.exoplayer.source.preload;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.source.preload.RankingDataComparator;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class c implements ListenerSet.Event, RankingDataComparator.InvalidationListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3787i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3788l;

    public /* synthetic */ c(Object obj, int i10) {
        this.f3787i = i10;
        this.f3788l = obj;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        switch (this.f3787i) {
            case 0:
                ((PreloadManagerListener) obj).onError((PreloadException) this.f3788l);
                break;
            default:
                ((PreloadManagerListener) obj).onError((PreloadException) this.f3788l);
                break;
        }
    }

    @Override // androidx.media3.exoplayer.source.preload.RankingDataComparator.InvalidationListener
    public void onRankingDataComparatorInvalidated() {
        ((BasePreloadManager) this.f3788l).invalidate();
    }
}
