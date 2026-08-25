package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class i implements ListenerSet.Event {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3479i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f3480l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3481m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f3482n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ long f3483o;

    public /* synthetic */ i(AnalyticsListener.EventTime eventTime, int i10, long j10, long j11, int i11) {
        this.f3479i = i11;
        this.f3480l = eventTime;
        this.f3481m = i10;
        this.f3482n = j10;
        this.f3483o = j11;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object obj) {
        switch (this.f3479i) {
            case 0:
                ((AnalyticsListener) obj).onBandwidthEstimate(this.f3480l, this.f3481m, this.f3482n, this.f3483o);
                break;
            default:
                ((AnalyticsListener) obj).onAudioUnderrun(this.f3480l, this.f3481m, this.f3482n, this.f3483o);
                break;
        }
    }
}
