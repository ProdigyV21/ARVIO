package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d implements ListenerSet.Event {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3464i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f3465l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ DecoderCounters f3466m;

    public /* synthetic */ d(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters, int i10) {
        this.f3464i = i10;
        this.f3465l = eventTime;
        this.f3466m = decoderCounters;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object obj) {
        switch (this.f3464i) {
            case 0:
                ((AnalyticsListener) obj).onAudioEnabled(this.f3465l, this.f3466m);
                break;
            case 1:
                ((AnalyticsListener) obj).onAudioDisabled(this.f3465l, this.f3466m);
                break;
            case 2:
                ((AnalyticsListener) obj).onVideoDisabled(this.f3465l, this.f3466m);
                break;
            default:
                ((AnalyticsListener) obj).onVideoEnabled(this.f3465l, this.f3466m);
                break;
        }
    }
}
