package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.source.MediaLoadData;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class r implements ListenerSet.Event {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3514i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f3515l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MediaLoadData f3516m;

    public /* synthetic */ r(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData, int i10) {
        this.f3514i = i10;
        this.f3515l = eventTime;
        this.f3516m = mediaLoadData;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object obj) {
        switch (this.f3514i) {
            case 0:
                ((AnalyticsListener) obj).onDownstreamFormatChanged(this.f3515l, this.f3516m);
                break;
            default:
                ((AnalyticsListener) obj).onUpstreamDiscarded(this.f3515l, this.f3516m);
                break;
        }
    }
}
