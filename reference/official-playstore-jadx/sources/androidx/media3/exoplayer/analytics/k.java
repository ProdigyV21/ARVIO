package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class k implements ListenerSet.Event {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3486i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f3487l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Exception f3488m;

    public /* synthetic */ k(AnalyticsListener.EventTime eventTime, Exception exc, int i10) {
        this.f3486i = i10;
        this.f3487l = eventTime;
        this.f3488m = exc;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object obj) {
        switch (this.f3486i) {
            case 0:
                ((AnalyticsListener) obj).onVideoCodecError(this.f3487l, this.f3488m);
                break;
            case 1:
                ((AnalyticsListener) obj).onDrmSessionManagerError(this.f3487l, this.f3488m);
                break;
            case 2:
                ((AnalyticsListener) obj).onAudioCodecError(this.f3487l, this.f3488m);
                break;
            default:
                ((AnalyticsListener) obj).onAudioSinkError(this.f3487l, this.f3488m);
                break;
        }
    }
}
