package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class n implements ListenerSet.Event {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3498i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f3499l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f3500m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f3501n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ long f3502o;

    public /* synthetic */ n(AnalyticsListener.EventTime eventTime, String str, long j10, long j11, int i10) {
        this.f3498i = i10;
        this.f3499l = eventTime;
        this.f3500m = str;
        this.f3501n = j10;
        this.f3502o = j11;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object obj) {
        switch (this.f3498i) {
            case 0:
                DefaultAnalyticsCollector.lambda$onAudioDecoderInitialized$4(this.f3499l, this.f3500m, this.f3501n, this.f3502o, (AnalyticsListener) obj);
                break;
            default:
                DefaultAnalyticsCollector.lambda$onVideoDecoderInitialized$16(this.f3499l, this.f3500m, this.f3501n, this.f3502o, (AnalyticsListener) obj);
                break;
        }
    }
}
