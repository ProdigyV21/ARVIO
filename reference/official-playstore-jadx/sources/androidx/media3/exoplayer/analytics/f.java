package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class f implements ListenerSet.Event {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3470i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f3471l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f3472m;

    public /* synthetic */ f(AnalyticsListener.EventTime eventTime, int i10, boolean z) {
        this.f3470i = i10;
        this.f3471l = eventTime;
        this.f3472m = z;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object obj) {
        switch (this.f3470i) {
            case 0:
                DefaultAnalyticsCollector.lambda$onIsLoadingChanged$35(this.f3471l, this.f3472m, (AnalyticsListener) obj);
                break;
            case 1:
                ((AnalyticsListener) obj).onSkipSilenceEnabledChanged(this.f3471l, this.f3472m);
                break;
            case 2:
                ((AnalyticsListener) obj).onIsPlayingChanged(this.f3471l, this.f3472m);
                break;
            default:
                ((AnalyticsListener) obj).onShuffleModeChanged(this.f3471l, this.f3472m);
                break;
        }
    }
}
