package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class h implements ListenerSet.Event {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3476i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f3477l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3478m;

    public /* synthetic */ h(AnalyticsListener.EventTime eventTime, int i10, int i11) {
        this.f3476i = i11;
        this.f3477l = eventTime;
        this.f3478m = i10;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object obj) {
        switch (this.f3476i) {
            case 0:
                ((AnalyticsListener) obj).onTimelineChanged(this.f3477l, this.f3478m);
                break;
            case 1:
                ((AnalyticsListener) obj).onPlaybackSuppressionReasonChanged(this.f3477l, this.f3478m);
                break;
            case 2:
                ((AnalyticsListener) obj).onDroppedSeeksWhileScrubbing(this.f3477l, this.f3478m);
                break;
            case 3:
                ((AnalyticsListener) obj).onPlaybackStateChanged(this.f3477l, this.f3478m);
                break;
            case 4:
                DefaultAnalyticsCollector.lambda$onDrmSessionAcquired$64(this.f3477l, this.f3478m, (AnalyticsListener) obj);
                break;
            case 5:
                ((AnalyticsListener) obj).onAudioSessionIdChanged(this.f3477l, this.f3478m);
                break;
            default:
                ((AnalyticsListener) obj).onRepeatModeChanged(this.f3477l, this.f3478m);
                break;
        }
    }
}
