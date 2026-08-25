package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class c implements ListenerSet.Event {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3462i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f3463l;

    public /* synthetic */ c(AnalyticsListener.EventTime eventTime, int i10) {
        this.f3462i = i10;
        this.f3463l = eventTime;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object obj) {
        switch (this.f3462i) {
            case 0:
                ((AnalyticsListener) obj).onPlayerReleased(this.f3463l);
                break;
            case 1:
                ((AnalyticsListener) obj).onDrmKeysRemoved(this.f3463l);
                break;
            case 2:
                ((AnalyticsListener) obj).onDrmKeysRestored(this.f3463l);
                break;
            case 3:
                ((AnalyticsListener) obj).onSeekStarted(this.f3463l);
                break;
            default:
                ((AnalyticsListener) obj).onDrmSessionReleased(this.f3463l);
                break;
        }
    }
}
