package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class o implements ListenerSet.Event {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3503i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f3504l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f3505m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f3506n;

    public /* synthetic */ o(AnalyticsListener.EventTime eventTime, int i10, long j10) {
        this.f3504l = eventTime;
        this.f3506n = i10;
        this.f3505m = j10;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object obj) {
        switch (this.f3503i) {
            case 0:
                ((AnalyticsListener) obj).onDroppedVideoFrames(this.f3504l, this.f3506n, this.f3505m);
                break;
            default:
                ((AnalyticsListener) obj).onVideoFrameProcessingOffset(this.f3504l, this.f3505m, this.f3506n);
                break;
        }
    }

    public /* synthetic */ o(AnalyticsListener.EventTime eventTime, long j10, int i10) {
        this.f3504l = eventTime;
        this.f3505m = j10;
        this.f3506n = i10;
    }
}
