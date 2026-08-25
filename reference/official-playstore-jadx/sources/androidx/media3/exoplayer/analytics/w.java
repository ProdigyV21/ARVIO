package androidx.media3.exoplayer.analytics;

import androidx.media3.common.Player;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSourceEventListener;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class w implements ListenerSet.Event, Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3531i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3532l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3533m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f3534n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f3535o;

    public /* synthetic */ w(AnalyticsListener.EventTime eventTime, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i10) {
        this.f3531i = 0;
        this.f3532l = eventTime;
        this.f3533m = i10;
        this.f3534n = positionInfo;
        this.f3535o = positionInfo2;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        ((MediaSourceEventListener.EventDispatcher) this.f3532l).lambda$loadStarted$0((LoadEventInfo) this.f3534n, (MediaLoadData) this.f3535o, this.f3533m, (MediaSourceEventListener) obj);
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        switch (this.f3531i) {
            case 0:
                DefaultAnalyticsCollector.lambda$onPositionDiscontinuity$46((AnalyticsListener.EventTime) this.f3532l, this.f3533m, (Player.PositionInfo) this.f3534n, (Player.PositionInfo) this.f3535o, (AnalyticsListener) obj);
                break;
            default:
                DefaultAnalyticsCollector.lambda$onLoadStarted$26((AnalyticsListener.EventTime) this.f3532l, (LoadEventInfo) this.f3534n, (MediaLoadData) this.f3535o, this.f3533m, (AnalyticsListener) obj);
                break;
        }
    }

    public /* synthetic */ w(Object obj, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i10, int i11) {
        this.f3531i = i11;
        this.f3532l = obj;
        this.f3534n = loadEventInfo;
        this.f3535o = mediaLoadData;
        this.f3533m = i10;
    }
}
