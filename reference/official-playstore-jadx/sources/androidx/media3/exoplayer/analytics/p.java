package androidx.media3.exoplayer.analytics;

import androidx.media3.common.PlaybackException;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class p implements ListenerSet.Event {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3507i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ AnalyticsListener.EventTime f3508l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ PlaybackException f3509m;

    public /* synthetic */ p(AnalyticsListener.EventTime eventTime, PlaybackException playbackException, int i10) {
        this.f3507i = i10;
        this.f3508l = eventTime;
        this.f3509m = playbackException;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object obj) {
        switch (this.f3507i) {
            case 0:
                ((AnalyticsListener) obj).onPlayerErrorChanged(this.f3508l, this.f3509m);
                break;
            default:
                ((AnalyticsListener) obj).onPlayerError(this.f3508l, this.f3509m);
                break;
        }
    }
}
